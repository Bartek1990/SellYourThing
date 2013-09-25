package com.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import model.Category;

@FacesValidator("categoryValidator")
public class CategoryValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component,Object value) throws ValidatorException {
        Category category = (Category) value;
        if (category == null) {
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Proszę wybrać kategorię", null));
        }

    }
}
