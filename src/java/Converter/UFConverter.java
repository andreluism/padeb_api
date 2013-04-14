/*
 * Copyright 2009 Prime Technology.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package Converter;

import Controller.FiltroController;
import Model.Regiao;
import Model.UF;
import Utils.test;
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;


@ManagedBean(name="UFConverter")
@RequestScoped
//@FacesConverter( value="UFConverter" )
public class UFConverter implements Converter {
    private List UFsDB;
    
    // Injetei o filtro do filtroController nesse converter.
    @ManagedProperty(value="#{filtroController}")
    private FiltroController filtroController;
    
    public void setFiltroController(FiltroController filtroController) {
        this.filtroController = filtroController;
    }
    
    public FiltroController getFiltroController() {
        return filtroController;
    }
    
    @PostConstruct
    public void init() {
        try {
            UFsDB = filtroController.getUFsDB();
        } catch (NullPointerException e) {
            UFsDB = new Utils.test().getUFs();
            filtroController.setUFsDB(UFsDB);
        }  
    }
    
    public UFConverter() {        
    }

    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(submittedValue);
                List<UF> _UFs = UFsDB;
                for (UF u : _UFs) {
                    if ((int) u.getUf() == number) {
                        return u;
                    }
                }
                
            } catch(NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid UF"));
            }
        }

        return null;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((UF) value).getUf());
        }
    }
}
