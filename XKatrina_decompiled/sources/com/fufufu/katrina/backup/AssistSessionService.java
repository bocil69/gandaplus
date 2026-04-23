package com.fufufu.katrina.backup;

import android.os.Bundle;
import android.service.voice.VoiceInteractionSession;
import android.service.voice.VoiceInteractionSessionService;
/* loaded from: classes5.dex */
public class AssistSessionService extends VoiceInteractionSessionService {
    @Override // android.service.voice.VoiceInteractionSessionService
    public VoiceInteractionSession onNewSession(Bundle bundle) {
        return new CopyService(this);
    }
}
