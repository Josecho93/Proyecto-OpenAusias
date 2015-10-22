/*
 * Copyright (c) 2015 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * openAUSIAS: The stunning micro-library that helps you to develop easily 
 * AJAX web applications by using Java and jQuery
 * openAUSIAS is distributed under the MIT License (MIT)
 * Sources at https://github.com/rafaelaznar/openAUSIAS
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * 
 */
package net.daw.dao.specific.implementation;
 
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import net.daw.bean.meta.MetaBeanGenImpl;
import net.daw.bean.specific.implementation.ProfesorBean;
import net.daw.dao.publicinterface.MetaDaoInterface;
import net.daw.dao.publicinterface.TableDaoInterface;
import net.daw.dao.publicinterface.ViewDaoInterface;
import net.daw.data.specific.implementation.MysqlDataSpImpl;
import net.daw.helper.annotations.MethodMetaInformation;
import net.daw.helper.annotations.SelectSourceMetaInformation;
import net.daw.helper.annotations.TableSourceMetaInformation;
import net.daw.helper.statics.ExceptionBooster;
import net.daw.helper.statics.FilterBeanHelper;
import net.daw.helper.statics.SqlBuilder;
 
public class ProfesorDao implements TableDaoInterface<ProfesorBean>, ViewDaoInterface<ProfesorBean>, MetaDaoInterface<ProfesorBean> {
 
    protected String strSqlSelectDataOrigin = null;
    protected String strTableOrigin = null;
    protected MysqlDataSpImpl oMysql = null;
    protected Connection oConnection = null;
 
    public ProfesorDao(Connection pooledConnection) throws Exception {
        try {
 
            Class classBEAN = ProfesorBean.class;
 
            if (classBEAN.isAnnotationPresent(TableSourceMetaInformation.class)) {
                TableSourceMetaInformation annotationTableSourceMetaInformation = (TableSourceMetaInformation) classBEAN.getAnnotation(TableSourceMetaInformation.class);
                strTableOrigin = annotationTableSourceMetaInformation.TableName();
                strSqlSelectDataOrigin = "select * from " + strTableOrigin + " where 1=1 ";
            }
            if (classBEAN.isAnnotationPresent(SelectSourceMetaInformation.class)) {
                SelectSourceMetaInformation annotationSelectSourceMetaInformation = (SelectSourceMetaInformation) classBEAN.getAnnotation(SelectSourceMetaInformation.class);
                strTableOrigin = null; //never used for news, updates or deletions
                strSqlSelectDataOrigin = "select * from ( " + annotationSelectSourceMetaInformation.SqlSelect() + " ) as origin01 where 1=1 ";
            }
            if (strSqlSelectDataOrigin.equals(null)) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":constructor ERROR: " + classBEAN.getName() + " Beans must be annotated by SelectSourceMetaInformation or TableSourceMetaInformation "));
            }
            oConnection = pooledConnection;
            oMysql = new MysqlDataSpImpl(oConnection);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":constructor ERROR: " + ex.getMessage()));
        }
    }
 
    @Override
    public ArrayList<MetaBeanGenImpl> getmetainformation() throws Exception {
        ArrayList<MetaBeanGenImpl> alVector = null;
        try {
            Class oProfesorBeanClass = ProfesorBean.class;
            alVector = new ArrayList<>();
            for (Field field : oProfesorBeanClass.getDeclaredFields()) {
                Annotation[] fieldAnnotations = field.getDeclaredAnnotations();
                for (Integer i = 0; i < fieldAnnotations.length; i++) {
                    if (fieldAnnotations[i].annotationType().equals(MethodMetaInformation.class)) {
                        MethodMetaInformation fieldAnnotation = (MethodMetaInformation) fieldAnnotations[i];
                        MetaBeanGenImpl oMeta = new MetaBeanGenImpl();
                        oMeta.setName(field.getName());
                        oMeta.setDefaultValue(fieldAnnotation.DefaultValue());
                        oMeta.setDescription(fieldAnnotation.Description());
                        oMeta.setIsId(fieldAnnotation.IsId());
                        oMeta.setIsObjForeignKey(fieldAnnotation.IsObjForeignKey());
                        oMeta.setMaxDecimal(fieldAnnotation.MaxDecimal());
                        oMeta.setMaxInteger(fieldAnnotation.MaxInteger());
                        oMeta.setMaxLength(fieldAnnotation.MaxLength());
                        oMeta.setMinLength(fieldAnnotation.MinLength());
                        oMeta.setMyIdName(fieldAnnotation.MyIdName());
                        oMeta.setReferencesTable(fieldAnnotation.ReferencesTable());
                        oMeta.setIsForeignKeyDescriptor(fieldAnnotation.IsForeignKeyDescriptor());
                        oMeta.setShortName(fieldAnnotation.ShortName());
                        oMeta.setType(fieldAnnotation.Type());
                        oMeta.setUltraShortName(fieldAnnotation.UltraShortName());
                        alVector.add(oMeta);
                    }
                }
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getmetainformation ERROR: " + ex.getMessage()));
        }
        return alVector;
    }
 
    @Override
    public ProfesorBean get(ProfesorBean oProfesorBean, Integer expand) throws Exception {
        if (oProfesorBean.getId() > 0) {
            if (oMysql.existsOne(strSqlSelectDataOrigin, oProfesorBean.getId())) {
                oProfesorBean.setNombre(oMysql.getOne(strSqlSelectDataOrigin, "nombre", oProfesorBean.getId()));
                oProfesorBean.setEstado(oMysql.getOne(strSqlSelectDataOrigin, "estado", oProfesorBean.getId()));
            }
        }
        return oProfesorBean;
    }
 
    @Override
    public ArrayList<ProfesorBean> getAll(ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder) throws Exception {
        strSqlSelectDataOrigin += SqlBuilder.buildSqlWhere(alFilter);
        strSqlSelectDataOrigin += SqlBuilder.buildSqlOrder(hmOrder);
        ResultSet oResultset = oMysql.getAllSql(strSqlSelectDataOrigin);
        ArrayList<ProfesorBean> alProfesores = new ArrayList<>();
        while (oResultset.next()) {
            int id = oResultset.getInt("id");
            ProfesorBean oProfesor = new ProfesorBean();
            oProfesor.setId(id);
            oProfesor.setNombre(oMysql.getOne(strSqlSelectDataOrigin, "nombre", id));
            oProfesor.setEstado(oMysql.getOne(strSqlSelectDataOrigin, "estado", id));
            alProfesores.add(oProfesor);
        }
        return alProfesores;
 
    }
 
    @Override
    public int getCount(ArrayList<FilterBeanHelper> alFilter) throws Exception {
        strSqlSelectDataOrigin += SqlBuilder.buildSqlWhere(alFilter);
        return oMysql.getCount(strSqlSelectDataOrigin);
    }
 
    @Override
    public ArrayList<ProfesorBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder) throws Exception {
        strSqlSelectDataOrigin += SqlBuilder.buildSqlWhere(alFilter);
        strSqlSelectDataOrigin += SqlBuilder.buildSqlOrder(hmOrder);
        ResultSet oResultset = oMysql.getPage(strSqlSelectDataOrigin, intRegsPerPag, intPage);
        ArrayList<ProfesorBean> alProfesores = new ArrayList<>();
        while (oResultset.next()) {
            int id = oResultset.getInt("id");
            ProfesorBean oProfesor = new ProfesorBean();
            oProfesor.setId(id);
            oProfesor.setNombre(oMysql.getOne(strSqlSelectDataOrigin, "nombre", id));
            oProfesor.setEstado(oMysql.getOne(strSqlSelectDataOrigin, "estado", id));
            alProfesores.add(oProfesor);
        }
        return alProfesores;
    }
 
    @Override
    public int getPages(int intRegsPerPag, ArrayList<FilterBeanHelper> alFilter) throws Exception {
        strSqlSelectDataOrigin += SqlBuilder.buildSqlWhere(alFilter);
        return oMysql.getPages(strSqlSelectDataOrigin, intRegsPerPag);
    }
 
    @Override
    public ProfesorBean set(ProfesorBean oProfesorBean) throws Exception {
        if (oProfesorBean.getId() == 0) {
            oProfesorBean.setId(oMysql.insertOne(strTableOrigin));
        }
        oMysql.updateOne(oProfesorBean.getId(), strTableOrigin, "nombre", oProfesorBean.getNombre());
        oMysql.updateOne(oProfesorBean.getId(), strTableOrigin, "estado", oProfesorBean.getEstado());
        return oProfesorBean;
    }
 
    @Override
    public int remove(ProfesorBean oProfesorBean) throws Exception {
        return oMysql.removeOne(oProfesorBean.getId(), strTableOrigin);
    }
 
}