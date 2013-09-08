
package com.beans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean
public class TestBean {
   	private List<Element> elements;
private String nowy;

    public String getNowy() {
        return nowy;
    }

    public void setNowy(String nowy) {
        Element el = new Element();
        el.setTitle(nowy);
        if(elements==null) elements = new ArrayList<>();
        elements.add(el);
        this.nowy = nowy;
    }
    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

}
