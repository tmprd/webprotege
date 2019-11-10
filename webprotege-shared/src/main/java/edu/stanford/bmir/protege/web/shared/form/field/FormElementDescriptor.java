package edu.stanford.bmir.protege.web.shared.form.field;

import com.fasterxml.jackson.annotation.*;
import com.google.auto.value.AutoValue;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableMap;
import edu.stanford.bmir.protege.web.shared.form.HasFormElementId;
import edu.stanford.bmir.protege.web.shared.lang.LanguageMap;
import org.semanticweb.owlapi.model.OWLProperty;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 30/03/16
 */
@JsonPropertyOrder({"id", "owlProperty", "label", "elementRun", "fieldDescriptor", "repeatability", "optionality", "help"})
@GwtCompatible(serializable = true)
@AutoValue
public abstract class FormElementDescriptor implements HasFormElementId, HasRepeatability, Serializable {


    @JsonCreator
    @Nonnull
    public static FormElementDescriptor get(@JsonProperty("id") @Nonnull FormElementId id,
                                            @JsonProperty("owlProperty") @Nullable OWLProperty property,
                                            @JsonProperty("label") @Nullable LanguageMap formLabel,
                                            @JsonProperty("elementRun") @Nullable ElementRun elementRun,
                                            @JsonProperty("fieldDescriptor") @Nonnull FormFieldDescriptor fieldDescriptor,
                                            @JsonProperty("repeatability") @Nullable Repeatability repeatability,
                                            @JsonProperty("optionality") @Nullable Optionality optionality,
                                            @JsonProperty("help") @Nullable LanguageMap help,
                                            @JsonProperty("style") @Nullable Map<String, String> style) {
        return new AutoValue_FormElementDescriptor(id,
                                                   property,
                                                   formLabel == null ? LanguageMap.empty() : formLabel,
                                                   elementRun == null ? ElementRun.START : elementRun,
                                                   fieldDescriptor,
                                                   optionality == null ? Optionality.REQUIRED : optionality,
                                                   repeatability == null ? Repeatability.NON_REPEATABLE : repeatability,
                                                   help == null ? LanguageMap.empty() : help,
                                                   style == null ? ImmutableMap.of() : style);
    }

    @Nonnull
    @Override
    @JsonProperty("id")
    public abstract FormElementId getId();

    @JsonIgnore
    @Nullable
    protected abstract OWLProperty getOwlPropertyInternal();

    @Nonnull
    public Optional<OWLProperty> getOwlProperty() {
        return Optional.ofNullable(getOwlPropertyInternal());
    }

    @Nonnull
    public abstract LanguageMap getLabel();

    @Nonnull
    public abstract ElementRun getElementRun();

    @Nonnull
    public abstract FormFieldDescriptor getFieldDescriptor();

    @Nonnull
    public abstract Optionality getOptionality();

    @Nonnull
    public abstract Repeatability getRepeatability();

    @Nonnull
    public abstract LanguageMap getHelp();

    @Nonnull
    public abstract Map<String, String> getStyle();
    
    @JsonIgnore
    public boolean isComposite() {
        return getFieldDescriptor() instanceof SubFormFieldDescriptor;
    }

    @JsonIgnore
    public boolean isNonComposite() {
        return !(getFieldDescriptor() instanceof SubFormFieldDescriptor);
    }
}
