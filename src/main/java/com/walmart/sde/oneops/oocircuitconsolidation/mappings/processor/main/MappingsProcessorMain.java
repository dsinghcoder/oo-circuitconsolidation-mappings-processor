package com.walmart.sde.oneops.oocircuitconsolidation.mappings.processor.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.walmart.sde.oneops.oocircuitconsolidation.mappings.processor.config.IConstants;
import com.walmart.sde.oneops.oocircuitconsolidation.mappings.processor.config.PostgressDbConnection;
import com.walmart.sde.oneops.oocircuitconsolidation.mappings.processor.dal.KloopzCmDal;
import com.walmart.sde.oneops.oocircuitconsolidation.mappings.processor.model.CmsCIRelationAndRelationAttributesActionMappingsModel;
import com.walmart.sde.oneops.oocircuitconsolidation.mappings.processor.model.CmsCiAndCmsCiAttributesActionMappingsModel;
import com.walmart.sde.oneops.oocircuitconsolidation.mappings.processor.util.CircuitconsolidationUtil;
import com.walmart.sde.oneops.oocircuitconsolidation.mappings.processor.util.MappingsCache;


public class MappingsProcessorMain {

  private final Logger log = LoggerFactory.getLogger(getClass());

  Properties props;

  public Properties loadTransformationConfigurations(String host, String user, String pwd) {


    log.info("loading transformation configurations...");


    props = new Properties();

    props.setProperty(IConstants.CMS_DB_HOST, System.getenv().get(IConstants.CMS_DB_HOST));
    props.setProperty(IConstants.CMS_DB_USER, System.getenv().get(IConstants.CMS_DB_USER));
    props.setProperty(IConstants.CMS_DB_PASS, System.getenv().get(IConstants.CMS_DB_PASS));

    log.info("loaded transformation configurations");
    return props;


  }


  public Properties getProps() {
    return props;
  }


  public void setProps(Properties props) {
    this.props = props;
  }



  public static void main(String[] args) {


  }

  public void processMappings(Map<String, List> transformationMappings, String ns,
      String platformName, String ooPhase, String envName, KloopzCmDal dal, int releaseId) {
    log.info("starting processing transformation mappings...");

    CMSCIMappingsProcessor cmsciMappingsProcessor = new CMSCIMappingsProcessor(ns,
        platformName, ooPhase, envName, dal, releaseId);
    
    cmsciMappingsProcessor.processCMSCIMappings(transformationMappings.get(IConstants.cmsCiMappingsMapKey));
    
    CMSCIRelationsMappingsProcessor  cmsciRelationsMappingsProcessor = new CMSCIRelationsMappingsProcessor(ns,
        platformName, ooPhase, envName, dal, releaseId);
    
    cmsciRelationsMappingsProcessor.processCMSCIRelationsMappings(transformationMappings.get(IConstants.cmsCiRelationsMappingsMapKey));
    log.info("Completed processing transformation mappings");
    
    
  }

  
}
