package com.walmart.sde.oneops.oocircuitconsolidation.mappings.processor.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.google.gson.Gson;
import com.walmart.sde.oneops.oocircuitconsolidation.mappings.processor.config.IConstants;
import com.walmart.sde.oneops.oocircuitconsolidation.mappings.processor.config.PostgressDbConnection;
import com.walmart.sde.oneops.oocircuitconsolidation.mappings.processor.util.MappingsCache;


public class MappingsProcessorTest {


  String host;
  String user;
  String pwd;

  private final Logger log = LoggerFactory.getLogger(getClass());


  private Gson gson = new Gson();

  @BeforeClass
  private void init() {

    host = System.getenv().get(IConstants.CMS_DB_HOST);
    user = System.getenv().get(IConstants.CMS_DB_USER);
    pwd = System.getenv().get(IConstants.CMS_DB_PASS);



  }


  @Test(enabled = true)
  private void testMappingsConfigFile() throws SQLException {
    
    /* Begin: These settings were used for development purpose
    String ns="/TestOrg2/guineapigs1";
    String platformName="guineapig-brown";
    String ooPhase=IConstants.DESIGN_PHASE;
    String envName=null; // null for design phase
    End: These settings were used for development purpose
    */
    /* Begin: These settings were used for development purpose
    String ns="/TestOrg2/guineapigs1";
    String platformName="guineapig-brown";
    String ooPhase=IConstants.TRANSITION_PHASE;
    String envName="dev"; // null for design phase
    */
    
    /*String ns="/TestOrg2/TestTransformationAssembly6";
    String platformName="TestCaseT6";
  
    
    */String ns="/TestOrg2/TestTransformtionOperatePhase1";
    String platformName="TestOpPhase1";
    
    String ooPhase=IConstants.OPERATE_PHASE;
    String envName="dev"; // null for design phase
    
    
    MappingsProcessorMain mappingsProcessorMain= new MappingsProcessorMain();
    
    
    Properties props = mappingsProcessorMain.loadTransformationConfigurations(host, user, pwd);
    Connection conn = PostgressDbConnection.getConnection(props);

    MappingsCache mappingsCache= new MappingsCache();
    
    Map<String, List> transformationMappings = mappingsCache.createTransformationMappingsCache(conn, ooPhase);
    log.info("transformationMappings: "+gson.toJson(transformationMappings));
    
    mappingsProcessorMain.processMappings(transformationMappings, ns, platformName, ooPhase, envName, conn);
    
    log.info("Transformation implemented, commiting transaction..");
    log.info("**********************************************************************");
    //conn.commit();
    log.info("Transformation committed to database");
    log.info("**********************************************************************");
    log.info("closing connection: ");
    conn.close();
    log.info("closed connection: ");
    
  }




}
