package com.example.utils.fotify_sca.issues_byCategory.javaFX;

import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.context.annotation.Configuration;
import com.example.utils.fotify_sca.issues_byCategory.model.source.*;
@Configuration
public class NativeHintsConfiguration implements RuntimeHintsRegistrar {

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        hints.reflection().registerType(
        		Chart.class,
            MemberCategory.values());
        hints.reflection().registerType(
        		GroupingSection.class,
             MemberCategory.values());
        hints.reflection().registerType(
        		Issue.class,
             MemberCategory.values());
        hints.reflection().registerType(
        		IssueListing.class,
             MemberCategory.values());	
        hints.reflection().registerType(
        		MajorAttributeSummary.class,
             MemberCategory.values());		
        hints.reflection().registerType(
        		MetaInfo.class,
             MemberCategory.values());
        hints.reflection().registerType(
        		Primary.class,
             MemberCategory.values());	
        hints.reflection().registerType(
        		ReportDefinition.class,
                MemberCategory.values());   
        hints.reflection().registerType(
        		ReportSection.class,
                MemberCategory.values());   
        hints.reflection().registerType(
        		SubSection.class,
                MemberCategory.values());  
        hints.reflection().registerType(
        		com.sun.javafx.scene.layout.region.BorderImageSlices.class,
                MemberCategory.values());
    }
}