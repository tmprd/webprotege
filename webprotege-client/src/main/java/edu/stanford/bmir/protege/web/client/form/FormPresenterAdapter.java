package edu.stanford.bmir.protege.web.client.form;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import edu.stanford.bmir.protege.web.shared.DirtyChangedHandler;
import edu.stanford.bmir.protege.web.shared.form.FormData;
import edu.stanford.bmir.protege.web.shared.form.FormDescriptor;
import edu.stanford.bmir.protege.web.shared.form.data.FormDataValue;

import javax.annotation.Nonnull;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-11-09
 */
public class FormPresenterAdapter implements FormElementEditor {

    @Nonnull
    private final SimplePanel container = new SimplePanel();

    @Nonnull
    private final FormDescriptor formDescriptor;

    @Nonnull
    private final FormPresenter formPresenter;

    public FormPresenterAdapter(@Nonnull FormDescriptor formDescriptor,
                                @Nonnull FormPresenter formPresenter) {
        this.formDescriptor = checkNotNull(formDescriptor);
        this.formPresenter = checkNotNull(formPresenter);
        container.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        container.getElement().getStyle().setBorderColor("#ff00ff");
    }

    public void start() {
        formPresenter.start(container);
        formPresenter.displayForm(formDescriptor, FormData.empty());
    }

    @Override
    public void setValue(FormDataValue object) {
        // TODO
//        formPresenter.displayForm(formDescriptor, object);
    }

    @Override
    public void clearValue() {
        formPresenter.clearData();
    }

    @Override
    public Optional<FormDataValue> getValue() {
        return Optional.empty();
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Optional<FormDataValue>> handler) {
        return () -> {};
    }

    @Override
    public Widget asWidget() {
        return formPresenter.getView().asWidget();
    }

    @Override
    public boolean isDirty() {
        return formPresenter.isDirty();
    }

    @Override
    public HandlerRegistration addDirtyChangedHandler(DirtyChangedHandler handler) {
        return () -> {};
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {

    }

    @Override
    public boolean isWellFormed() {
        return true;
    }
}
