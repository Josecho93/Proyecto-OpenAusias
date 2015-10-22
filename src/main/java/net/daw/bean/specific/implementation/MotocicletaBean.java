/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.bean.specific.implementation;

import com.google.gson.annotations.Expose;
import net.daw.bean.generic.implementation.BeanGenImpl;
import net.daw.bean.publicinterface.BeanInterface;
import net.daw.helper.annotations.MethodMetaInformation;
import net.daw.helper.statics.MetaEnum;

/**
 *
 * @author a044533450e
 */
public class MotocicletaBean extends BeanGenImpl implements BeanInterface {
    
    @Expose
    @MethodMetaInformation(
            IsId = true,
            UltraShortName = "Iden.",
            ShortName = "Identif.",
            Description = "Número Identificador",
            Type = MetaEnum.FieldType.Integer,
            DefaultValue = "0"
    )
    private Integer id = 0; //siempre inicializar los id a 0
 
    @Expose
    @MethodMetaInformation(            
            UltraShortName = "Mar.",
            ShortName = "Marca",
            Description = "Marca de la motocicleta",
            Type = MetaEnum.FieldType.String
        
    )
    private String marca;
    @Expose
    @MethodMetaInformation(            
            UltraShortName = "Mod.",
            ShortName = "Modelo",
            Description = "Modelo de la motocicleta",
            Type = MetaEnum.FieldType.String
          
    )
    private String modelo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    
    
}
