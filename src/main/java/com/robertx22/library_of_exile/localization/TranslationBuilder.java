package com.robertx22.library_of_exile.localization;

import com.robertx22.library_of_exile.main.ExileLog;

import java.util.HashMap;
import java.util.Map;

public class TranslationBuilder {

    // modid is for which mod's lang file this translation goes to
    public String modid;
    public HashMap<TranslationType, ExileTranslation> all = new HashMap<>();

    public TranslationBuilder(String modid) {
        this.modid = modid;
    }

    public static TranslationBuilder of(String modid) {
        return new TranslationBuilder(modid);
    }

    public TranslationBuilder name(ExileTranslation tra) {
        all.put(TranslationType.NAME, tra);
        return this;
    }

    public TranslationBuilder desc(ExileTranslation tra) {
        all.put(TranslationType.DESCRIPTION, tra);
        return this;
    }

    public void build() {

        if (!ExileLangFile.all.containsKey(modid)) {
            ExileLangFile.all.put(modid, new HashMap<>());
        }
        for (Map.Entry<TranslationType, ExileTranslation> en : all.entrySet()) {
            var tra = en.getValue();
            if (tra.locname != null && !tra.locname.isEmpty()) {
                ExileLangFile.all.get(modid).put(tra.key, tra);
            } else {
                ExileLog.get().warn(tra.key + " is empty or null!");
            }
        }
    }
}
