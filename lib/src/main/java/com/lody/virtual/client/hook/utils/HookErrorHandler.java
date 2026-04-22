package com.lody.virtual.client.hook.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.lody.virtual.client.VClient;
import com.lody.virtual.client.core.VirtualCore;

/**
 * Comprehensive Error Handler for Virtual App Hooking Layer.
 * Catches and displays all errors with detailed information for debugging.
 */
public class HookErrorHandler {
    
    private static final String TAG = "GandaPlusError";
    private static final boolean SHOW_DIALOG = true;  // Set to false for production
    private static final boolean SHOW_TOAST = true;
    
    /**
     * Error types for categorization
     */
    public enum ErrorType {
        HOOK_INJECTION_FAILED,
        SPOOF_DATA_MISSING,
        LOCATION_DATA_MISSING,
        REMOTE_EXCEPTION,
        PERMISSION_DENIED,
        PROCESS_START_FAILED,
        ACTIVITY_LAUNCH_FAILED,
        UNKNOWN
    }
    
    /**
     * Main error handler method - call this from anywhere
     */
    public static void handleError(ErrorType type, String context, Throwable e) {
        String detailedMessage = buildDetailedMessage(type, context, e);
        
        // Always log to console
        Log.e(TAG, detailedMessage, e);
        
        // Show to user
        showErrorToUser(type, detailedMessage);
    }
    
    /**
     * Handle error with custom message
     */
    public static void handleError(String customMessage, Throwable e) {
        Log.e(TAG, customMessage, e);
        showErrorToUser(ErrorType.UNKNOWN, customMessage + "\n\nException: " + (e != null ? e.getMessage() : "N/A"));
    }
    
    /**
     * Check if all required data is available before launching
     */
    public static boolean validateBeforeLaunch(String pkg, int userId) {
        StringBuilder missing = new StringBuilder();
        
        // Check if package is valid
        if (pkg == null || pkg.isEmpty()) {
            missing.append("• Package name is empty\n");
        }
        
        // Check spoof data
        try {
            com.lody.virtual.remote.VDeviceConfig config = 
                com.lody.virtual.client.ipc.VDeviceManager.get().getDeviceConfig(userId);
            if (config == null || !config.enable) {
                missing.append("• Device spoof not configured (userId=" + userId + ")\n");
            }
        } catch (Exception e) {
            missing.append("• Cannot read device config: " + e.getMessage() + "\n");
        }
        
        // Check location data
        try {
            int mode = com.lody.virtual.client.ipc.VirtualLocationManager.get()
                .getMode(userId, pkg);
            if (mode == com.lody.virtual.client.ipc.VirtualLocationManager.MODE_USE_SELF) {
                com.lody.virtual.remote.vloc.VLocation location = com.lody.virtual.client.ipc.VLocationManager.get()
                        .getLocation(pkg, userId);
                if (location == null || location.isEmpty()) {
                    missing.append("• Fake location is enabled but the coordinates are empty\n");
                }
            }
        } catch (Exception e) {
            missing.append("• Cannot read location mode: " + e.getMessage() + "\n");
        }
        
        if (missing.length() > 0) {
            String message = "⚠️ Validation Failed for " + pkg + ":\n\n" + missing.toString();
            handleError(ErrorType.SPOOF_DATA_MISSING, message, null);
            return false;
        }
        
        return true;
    }
    
    /**
     * Build detailed error message with stack trace
     */
    private static String buildDetailedMessage(ErrorType type, String context, Throwable e) {
        StringBuilder sb = new StringBuilder();
        sb.append("╔══════════════════════════════════════════════════════════════╗\n");
        sb.append("║  GANDA PLUS ERROR REPORT                                     ║\n");
        sb.append("╠══════════════════════════════════════════════════════════════╣\n");
        sb.append("Type: ").append(type.name()).append("\n");
        sb.append("Context: ").append(context).append("\n");
        try {
            if (com.lody.virtual.client.core.VirtualCore.get() != null && com.lody.virtual.client.core.VirtualCore.get().isVAppProcess()) {
                sb.append("Package: ").append(VClient.get().getCurrentPackage()).append("\n");
            } else {
                sb.append("Package: (Main Process)\n");
            }
        } catch (Throwable t) {
            sb.append("Package: (Unknown)\n");
        }
        sb.append("UserId: ").append(com.lody.virtual.os.VUserHandle.myUserId()).append("\n");
        sb.append("Process: ").append(android.os.Process.myPid()).append("\n");
        
        if (e != null) {
            sb.append("Exception: ").append(e.getClass().getName()).append("\n");
            sb.append("Message: ").append(e.getMessage()).append("\n");
            
            // Add first 5 stack trace elements
            StackTraceElement[] stack = e.getStackTrace();
            if (stack != null && stack.length > 0) {
                sb.append("Stack:\n");
                for (int i = 0; i < Math.min(5, stack.length); i++) {
                    sb.append("  at ").append(stack[i].toString()).append("\n");
                }
            }
        }
        
        sb.append("╚══════════════════════════════════════════════════════════════╝");
        
        return sb.toString();
    }
    
    /**
     * Display error to user via Toast and/or Dialog
     */
    private static void showErrorToUser(final ErrorType type, final String message) {
        // Get main looper for UI updates
        new Handler(Looper.getMainLooper()).post(() -> {
            Context context = VirtualCore.get().getContext();
            
            if (SHOW_TOAST && context != null) {
                Toast.makeText(context, "⚠️ " + type.name(), Toast.LENGTH_LONG).show();
            }
            
            if (SHOW_DIALOG && context instanceof Activity) {
                try {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("🔴 Ganda Plus Error: " + type.name());
                    builder.setMessage(message);
                    builder.setPositiveButton("Copy to Clipboard", (dialog, which) -> {
                        android.content.ClipboardManager clipboard = 
                            (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                        android.content.ClipData clip = android.content.ClipData.newPlainText("Error Report", message);
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(context, "Error copied! Share with developer.", Toast.LENGTH_SHORT).show();
                    });
                    builder.setNegativeButton("Dismiss", null);
                    builder.setCancelable(false);
                    builder.show();
                } catch (Exception e) {
                    Log.e(TAG, "Failed to show error dialog", e);
                }
            }
        });
    }
    
    /**
     * Wrap any Runnable with error handling
     */
    public static Runnable wrapWithErrorHandling(Runnable task, String context) {
        return () -> {
            try {
                task.run();
            } catch (Throwable e) {
                handleError(ErrorType.UNKNOWN, context, e);
                throw e;
            }
        };
    }
    
    /**
     * Safe executor - executes task and catches all errors
     */
    public static boolean safeExecute(String context, Runnable task) {
        try {
            task.run();
            return true;
        } catch (Throwable e) {
            handleError(ErrorType.UNKNOWN, context, e);
            return false;
        }
    }
}
