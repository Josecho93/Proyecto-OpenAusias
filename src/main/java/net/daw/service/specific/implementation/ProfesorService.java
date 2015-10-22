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
package net.daw.service.specific.implementation;
  
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.specific.implementation.ProfesorBean;
import net.daw.bean.specific.implementation.UsuarioBean;
import net.daw.connection.publicinterface.ConnectionInterface;
import net.daw.dao.specific.implementation.ProfesorDao;
import net.daw.helper.statics.AppConfigurationHelper;
import static net.daw.helper.statics.AppConfigurationHelper.getSourceConnection;
import net.daw.helper.statics.ExceptionBooster;
import net.daw.helper.statics.FilterBeanHelper;
import net.daw.helper.statics.JsonMessage;
import net.daw.helper.statics.ParameterCook;
import net.daw.service.publicinterface.MetaServiceInterface;
import net.daw.service.publicinterface.TableServiceInterface;
import net.daw.service.publicinterface.ViewServiceInterface;
  
public class ProfesorService implements TableServiceInterface, ViewServiceInterface, MetaServiceInterface {
  
    private HttpServletRequest oRequest = null;
  
    public ProfesorService(HttpServletRequest request) {
        oRequest = request;
    }
  
    @Override
    public Boolean checkpermission(String strMethodName) throws Exception {
        UsuarioBean oUserBean = (UsuarioBean) oRequest.getSession().getAttribute("userBean");
        if (oUserBean != null) {
            return true;
        } else {
            return false;
        }
    }
  
    @Override
    public String getmetainformation() throws Exception {
        if (this.checkpermission("getmetainformation")) {
            Connection oConnection = null;
            ConnectionInterface oDataConnectionSource = null;
            ProfesorDao oProfesorDao = null;
            String strResult = null;
            try {
                oDataConnectionSource = getSourceConnection();
                oConnection = oDataConnectionSource.newConnection();
                oProfesorDao = new ProfesorDao(oConnection);
                strResult = AppConfigurationHelper.getGson().toJson(oProfesorDao.getmetainformation());
            } catch (Exception ex) {
                oConnection.rollback();
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getmetainformation ERROR: " + ex.getMessage()));
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oDataConnectionSource != null) {
                    oDataConnectionSource.disposeConnection();
                }
            }
            return strResult;
        } else {
            return JsonMessage.getJsonMsg("401", "Unauthorized");
        }
    }
  
    @Override
    public String get() throws Exception {
        if (this.checkpermission("get")) {
  
            Connection oConnection = null;
            ConnectionInterface oDataConnectionSource = null;
            String strResult = null;
            try {
                oDataConnectionSource = getSourceConnection();
                oConnection = oDataConnectionSource.newConnection();
                int id = ParameterCook.prepareId(oRequest);
                ProfesorDao oProfesorDao = new ProfesorDao(oConnection);
                ProfesorBean oProfesorBean = new ProfesorBean();
                oProfesorBean.setId(id);
                oProfesorBean = oProfesorDao.get(oProfesorBean, 1);
                strResult = AppConfigurationHelper.getGson().toJson(oProfesorBean);
            } catch (Exception ex) {
                oConnection.rollback();
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":remove ERROR: " + ex.getMessage()));
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oDataConnectionSource != null) {
                    oDataConnectionSource.disposeConnection();
                }
            }
            return strResult;
        } else {
            return JsonMessage.getJsonMsg("401", "Unauthorized");
        }
    }
  
    @Override
    public String getall() throws Exception {
        if (this.checkpermission("getall")) {
            Connection oConnection = null;
            ConnectionInterface oDataConnectionSource = null;
            String strResult = null;
            try {
                oDataConnectionSource = getSourceConnection();
                oConnection = oDataConnectionSource.newConnection();
                ProfesorDao oProfesorDao = new ProfesorDao(oConnection);
  
                ArrayList<FilterBeanHelper> alFilterBeanHelper = ParameterCook.prepareFilter(oRequest);
                HashMap<String, String> hmOrder = ParameterCook.prepareOrder(oRequest);
  
                ArrayList<ProfesorBean> alProfesorBean = oProfesorDao.getAll(alFilterBeanHelper, hmOrder);
                strResult = AppConfigurationHelper.getGson().toJson(alProfesorBean);
            } catch (Exception ex) {
                oConnection.rollback();
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":remove ERROR: " + ex.getMessage()));
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oDataConnectionSource != null) {
                    oDataConnectionSource.disposeConnection();
                }
            }
            return strResult;
        } else {
            return JsonMessage.getJsonMsg("401", "Unauthorized");
        }
    }
  
    @Override
    public String getcount() throws Exception {
        if (this.checkpermission("getcount")) {
            Connection oConnection = null;
            ConnectionInterface oDataConnectionSource = null;
            String strResult = null;
            try {
                oDataConnectionSource = getSourceConnection();
                oConnection = oDataConnectionSource.newConnection();
                ProfesorDao oProfesorDao = new ProfesorDao(oConnection);
  
                ArrayList<FilterBeanHelper> alFilterBeanHelper = ParameterCook.prepareFilter(oRequest);
  
                strResult = ((Integer) oProfesorDao.getCount(alFilterBeanHelper)).toString();
            } catch (Exception ex) {
                oConnection.rollback();
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":remove ERROR: " + ex.getMessage()));
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oDataConnectionSource != null) {
                    oDataConnectionSource.disposeConnection();
                }
            }
            return strResult;
        } else {
            return JsonMessage.getJsonMsg("401", "Unauthorized");
        }
    }
  
    @Override
    public String getpage() throws Exception {
        if (this.checkpermission("getpage")) {
            Connection oConnection = null;
            ConnectionInterface oDataConnectionSource = null;
            String strResult = null;
            try {
                oDataConnectionSource = getSourceConnection();
                oConnection = oDataConnectionSource.newConnection();
                ProfesorDao oProfesorDao = new ProfesorDao(oConnection);
                int intPage = ParameterCook.preparePage(oRequest);
                int intRpp = ParameterCook.prepareRpp(oRequest);
                ArrayList<FilterBeanHelper> alFilterBeanHelper = ParameterCook.prepareFilter(oRequest);
                HashMap<String, String> hmOrder = ParameterCook.prepareOrder(oRequest);
                ArrayList<ProfesorBean> alProfesorBean = oProfesorDao.getPage(intRpp, intPage, alFilterBeanHelper, hmOrder);
                strResult = AppConfigurationHelper.getGson().toJson(alProfesorBean);
            } catch (Exception ex) {
                oConnection.rollback();
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":remove ERROR: " + ex.getMessage()));
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oDataConnectionSource != null) {
                    oDataConnectionSource.disposeConnection();
                }
            }
            return strResult;
        } else {
            return JsonMessage.getJsonMsg("401", "Unauthorized");
        }
    }
  
    @Override
    public String getpages() throws Exception {
        if (this.checkpermission("getpages")) {
            Connection oConnection = null;
            ConnectionInterface oDataConnectionSource = null;
            String strResult = null;
            try {
                oDataConnectionSource = getSourceConnection();
                oConnection = oDataConnectionSource.newConnection();
                ProfesorDao oProfesorDao = new ProfesorDao(oConnection);
                int intRpp = ParameterCook.prepareRpp(oRequest);
                ArrayList<FilterBeanHelper> alFilterBeanHelper = ParameterCook.prepareFilter(oRequest);
                strResult = ((Integer) oProfesorDao.getPages(intRpp, alFilterBeanHelper)).toString();
            } catch (Exception ex) {
                oConnection.rollback();
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":remove ERROR: " + ex.getMessage()));
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oDataConnectionSource != null) {
                    oDataConnectionSource.disposeConnection();
                }
            }
            return strResult;
        } else {
            return JsonMessage.getJsonMsg("401", "Unauthorized");
        }
    }
  
    @Override
    public String getaggregateviewone() throws Exception {
        if (this.checkpermission("getaggregateviewone")) {
            String data = null;
            try {
                String meta = this.getmetainformation();
                String one = this.get();
                data = "{"
                        + "\"meta\":" + meta
                        + ",\"bean\":" + one
                        + "}";
                data = JsonMessage.getJson("200", data);
                return data;
            } catch (Exception ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getAggregateViewOne ERROR: " + ex.getMessage()));
            }
            return data;
        } else {
            return JsonMessage.getJsonMsg("401", "Unauthorized");
        }
    }
  
    @Override
    public String getaggregateviewsome() throws Exception {
        if (this.checkpermission("getaggregateviewsome")) {
            String data = null;
            try {
                String meta = this.getmetainformation();
                String page = this.getpage();
                String pages = this.getpages();
                String registers = this.getcount();
                data = "{"
                        + "\"meta\":" + meta
                        + ",\"page\":" + page
                        + ",\"pages\":" + pages
                        + ",\"registers\":" + registers
                        + "}";
                data = JsonMessage.getJson("200", data);
            } catch (Exception ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getAggregateViewSome ERROR: " + ex.getMessage()));
            }
            return data;
        } else {
            return JsonMessage.getJsonMsg("401", "Unauthorized");
        }
    }
  
    @Override
    public String getaggregateviewall() throws Exception {
        if (this.checkpermission("getaggregateviewall")) {
            String data = null;
            try {
                String meta = this.getmetainformation();
                String all = this.getall();
                String registers = this.getcount();
                data = "{"
                        + "\"meta\":" + meta
                        + ",\"page\":" + all
                        + ",\"registers\":" + registers
                        + "}";
                data = JsonMessage.getJson("200", data);
            } catch (Exception ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getAggregateViewAll ERROR: " + ex.getMessage()));
            }
            return data;
        } else {
            return JsonMessage.getJsonMsg("401", "Unauthorized");
        }
    }
  
    @Override
    public String remove() throws Exception {
        if (this.checkpermission("remove")) {
            Connection oConnection = null;
            ConnectionInterface oDataConnectionSource = null;
            String strResult = null;
            try {
                oDataConnectionSource = getSourceConnection();
                oConnection = oDataConnectionSource.newConnection();
                int id = ParameterCook.prepareId(oRequest);
                ProfesorDao oProfesorDao = new ProfesorDao(oConnection);
                ProfesorBean oProfesorBean = new ProfesorBean();
                oProfesorBean.setId(id);
                strResult = ((Integer) oProfesorDao.remove(oProfesorBean)).toString();
            } catch (Exception ex) {
                oConnection.rollback();
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":remove ERROR: " + ex.getMessage()));
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oDataConnectionSource != null) {
                    oDataConnectionSource.disposeConnection();
                }
            }
            return strResult;
        } else {
            return JsonMessage.getJsonMsg("401", "Unauthorized");
        }
    }
  
    @Override
    public String set() throws Exception {
        if (this.checkpermission("set")) {
            ProfesorBean oProfesorBean = null;
            Connection oConnection = null;
            ConnectionInterface oDataConnectionSource = null;
            String strResult = null;
            try {
                oDataConnectionSource = getSourceConnection();
                oConnection = oDataConnectionSource.newConnection();
                oConnection.setAutoCommit(false);
                ProfesorDao oProfesorDao = new ProfesorDao(oConnection);
                oProfesorBean = new ProfesorBean();
                String strJson = ParameterCook.prepareJson(oRequest);
                oProfesorBean = AppConfigurationHelper.getGson().fromJson(strJson, ProfesorBean.class);
                oProfesorBean = oProfesorDao.set(oProfesorBean);
                oConnection.commit();
                strResult = ((Integer) oProfesorBean.getId()).toString();
            } catch (Exception ex) {
                oConnection.rollback();
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":remove ERROR: " + ex.getMessage()));
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oDataConnectionSource != null) {
                    oDataConnectionSource.disposeConnection();
                }
            }
            return strResult;
        } else {
            return JsonMessage.getJsonMsg("401", "Unauthorized");
        }
    }
  
}