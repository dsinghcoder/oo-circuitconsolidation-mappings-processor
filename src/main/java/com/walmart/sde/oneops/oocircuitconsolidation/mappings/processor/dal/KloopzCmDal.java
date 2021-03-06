package com.walmart.sde.oneops.oocircuitconsolidation.mappings.processor.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.walmart.sde.oneops.oocircuitconsolidation.mappings.processor.config.IConstants;
import com.walmart.sde.oneops.oocircuitconsolidation.mappings.processor.exception.UnSupportedOperation;

public class KloopzCmDal {

  private final Logger log = LoggerFactory.getLogger(getClass());

  Connection conn;


  public KloopzCmDal(Connection conn) {
    this.conn = conn;

  }

  public Connection getConn() {
    return conn;
  }


  public void setConn(Connection conn) {
    this.conn = conn;
  }


  public List<Integer> getCiIdsForNsAndClazz(String ns, String clazz) {

    List<Integer> ciIds = new ArrayList<Integer>();
    try {

      String SQL_SELECT_NakedCMSCIByNsAndClazz = "select " + "ci.ci_id as ciId, "
          + "ci.ci_name as ciName," + "ci.class_id as ciClassId," + "cl.class_name as ciClassName,"
          + "cl.impl as impl, " + "ci.ns_id as nsId, " + "ns.ns_path as nsPath, "
          + "ci.ci_goid as ciGoid, " + "ci.comments, " + "ci.ci_state_id as ciStateId, "
          + "st.state_name as ciState, " + "ci.last_applied_rfc_id as lastAppliedRfcId, "
          + "ci.created_by as createdBy, " + "ci.updated_by as updatedBy, " + "ci.created, "
          + "ci.updated " + "from cm_ci ci, md_classes cl, ns_namespaces ns, cm_ci_state st "
          + "where ns.ns_path = ? " + "and cl.class_name = ? " + "and ci.class_id = cl.class_id "
          + "and ci.ns_id = ns.ns_id " + "and ci.ci_state_id = st.ci_state_id;";

      log.info("SQL_SELECT_NakedCMSCIByNsAndClazz : " + SQL_SELECT_NakedCMSCIByNsAndClazz);
      PreparedStatement preparedStatement_SELECT_NakedCMSCIByNsAndClazz =
          conn.prepareStatement(SQL_SELECT_NakedCMSCIByNsAndClazz);
      preparedStatement_SELECT_NakedCMSCIByNsAndClazz.setString(1, ns);
      preparedStatement_SELECT_NakedCMSCIByNsAndClazz.setString(2, clazz);

      log.info("preparedStatement_SELECT_NakedCMSCIByNsAndClazz: "
          + preparedStatement_SELECT_NakedCMSCIByNsAndClazz);
      ResultSet resultSet = preparedStatement_SELECT_NakedCMSCIByNsAndClazz.executeQuery();

      int numberOfRecords = 0;
      while (resultSet.next()) {
        ciIds.add(resultSet.getInt("ciId"));
        numberOfRecords++;

      }

      log.info("numberOfRecords: " + numberOfRecords);
    } catch (Exception e) {
      throw new UnSupportedOperation("Error while fetching records" + e.getMessage());
    }
    return ciIds;
  }

  // Method getCiIdsForNsAndClazz can be replaced by this method
  public Map<String, Integer> getCiNamesAndCiIdsMapForNsAndClazz(String ns, String clazz) {

    Map<String, Integer> ciIdsAndCiNamesMap = new HashMap<String, Integer>();
    try {

      String SQL_SELECT_NakedCMSCIByNsAndClazz = "select " + "ci.ci_id as ciId, "
          + "ci.ci_name as ciName," + "ci.class_id as ciClassId," + "cl.class_name as ciClassName,"
          + "cl.impl as impl, " + "ci.ns_id as nsId, " + "ns.ns_path as nsPath, "
          + "ci.ci_goid as ciGoid, " + "ci.comments, " + "ci.ci_state_id as ciStateId, "
          + "st.state_name as ciState, " + "ci.last_applied_rfc_id as lastAppliedRfcId, "
          + "ci.created_by as createdBy, " + "ci.updated_by as updatedBy, " + "ci.created, "
          + "ci.updated " + "from cm_ci ci, md_classes cl, ns_namespaces ns, cm_ci_state st "
          + "where ns.ns_path = ? " + "and cl.class_name = ? " + "and ci.class_id = cl.class_id "
          + "and ci.ns_id = ns.ns_id " + "and ci.ci_state_id = st.ci_state_id;";

      log.info("SQL_SELECT_NakedCMSCIByNsAndClazz : " + SQL_SELECT_NakedCMSCIByNsAndClazz);
      PreparedStatement preparedStatement_SELECT_NakedCMSCIByNsAndClazz =
          conn.prepareStatement(SQL_SELECT_NakedCMSCIByNsAndClazz);
      preparedStatement_SELECT_NakedCMSCIByNsAndClazz.setString(1, ns);
      preparedStatement_SELECT_NakedCMSCIByNsAndClazz.setString(2, clazz);

      log.info("preparedStatement_SELECT_NakedCMSCIByNsAndClazz: "
          + preparedStatement_SELECT_NakedCMSCIByNsAndClazz);
      ResultSet resultSet = preparedStatement_SELECT_NakedCMSCIByNsAndClazz.executeQuery();

      int numberOfRecords = 0;
      while (resultSet.next()) {
        ciIdsAndCiNamesMap.put(resultSet.getString("ciName"), resultSet.getInt("ciId"));
        numberOfRecords++;

      }

      log.info("numberOfRecords: " + numberOfRecords);
    } catch (Exception e) {
      throw new UnSupportedOperation("Error while fetching records" + e.getMessage());
    }
    return ciIdsAndCiNamesMap;
  }


  public Map<Integer, String> getCiIdsAndCiNameForNsAndClazzMap(String ns, String clazz) {

    Map<Integer, String> ciIdAndCiNameMap = new HashMap<Integer, String>();
    try {

      String SQL_SELECT_NakedCMSCIByNsAndClazz = "select " + "ci.ci_id as ciId, "
          + "ci.ci_name as ciName," + "ci.class_id as ciClassId," + "cl.class_name as ciClassName,"
          + "cl.impl as impl, " + "ci.ns_id as nsId, " + "ns.ns_path as nsPath, "
          + "ci.ci_goid as ciGoid, " + "ci.comments, " + "ci.ci_state_id as ciStateId, "
          + "st.state_name as ciState, " + "ci.last_applied_rfc_id as lastAppliedRfcId, "
          + "ci.created_by as createdBy, " + "ci.updated_by as updatedBy, " + "ci.created, "
          + "ci.updated " + "from cm_ci ci, md_classes cl, ns_namespaces ns, cm_ci_state st "
          + "where ns.ns_path = ? " + "and cl.class_name = ? " + "and ci.class_id = cl.class_id "
          + "and ci.ns_id = ns.ns_id " + "and ci.ci_state_id = st.ci_state_id;";

      log.info("SQL_SELECT_NakedCMSCIByNsAndClazz : " + SQL_SELECT_NakedCMSCIByNsAndClazz);
      PreparedStatement preparedStatement_SELECT_NakedCMSCIByNsAndClazz =
          conn.prepareStatement(SQL_SELECT_NakedCMSCIByNsAndClazz);
      preparedStatement_SELECT_NakedCMSCIByNsAndClazz.setString(1, ns);
      preparedStatement_SELECT_NakedCMSCIByNsAndClazz.setString(2, clazz);

      log.info("preparedStatement_SELECT_NakedCMSCIByNsAndClazz: "
          + preparedStatement_SELECT_NakedCMSCIByNsAndClazz);
      ResultSet resultSet = preparedStatement_SELECT_NakedCMSCIByNsAndClazz.executeQuery();

      int numberOfRecords = 0;
      while (resultSet.next()) {
        ciIdAndCiNameMap.put(resultSet.getInt("ciId"), resultSet.getString("ciName"));
        numberOfRecords++;

      }

      log.info("numberOfRecords: " + numberOfRecords);
    } catch (Exception e) {
      throw new UnSupportedOperation("Error while fetching records" + e.getMessage());
    }
    return ciIdAndCiNameMap;
  }

  public void deleteCmsCisForNsAndClazz(String nsForPlatformCiComponents, String clazz) {

    try {

      String SQL_deleteCmsCisForNsAndClazz = "DELETE from cm_ci where ci_id in "
          + "( SELECT ci.ci_id FROM cm_ci ci, md_classes cl, ns_namespaces ns, cm_ci_state st "
          + "where ns.ns_path = ? " + "and cl.class_name = ? " + "and ci.class_id = cl.class_id "
          + "and ci.ns_id = ns.ns_id " + "and ci.ci_state_id = st.ci_state_id );";

      log.info("SQL_deleteCmsCisForNsAndClazz: " + SQL_deleteCmsCisForNsAndClazz);
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_deleteCmsCisForNsAndClazz);
      preparedStatement.setString(1, nsForPlatformCiComponents);
      preparedStatement.setString(2, clazz);
      log.info("preparedStatement: " + preparedStatement);
      int NumberOfCmsCiDeleted = preparedStatement.executeUpdate();
      log.info("NumberOfCmsCiDeleted <{}> for nsForPlatformCiComponents <{}> and clazz <{}>",
          NumberOfCmsCiDeleted, nsForPlatformCiComponents, clazz);


    } catch (Exception e) {
      throw new UnSupportedOperation("Error while fetching records" + e.getMessage());
    }

  }

  public void deleteCiRelations(String fromClazz, List<Integer> fromCiIds, String toClazz,
      List<Integer> toCiIds, int sourceCmsCiRelationId, String relationName,
      String nsForPlatformCiComponents) {
    int NumberOfCmsCiRelationsDeleted = 0;
    try {
      for (int fromCiId : fromCiIds) {

        for (int toCiId : toCiIds) {

          String SQL_DELETE_CMSCIRelations =
              "delete from cm_ci_relations ci_rel where ns_id = (select ns_id from ns_namespaces ns where ns.ns_path=?) "
                  + " and ci_rel.relation_id=? " + " and ci_rel.from_ci_id =? "
                  + " and ci_rel.to_ci_id=?;";


          log.info("SQL_DELETE_CMSCIRelations: " + SQL_DELETE_CMSCIRelations);
          PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE_CMSCIRelations);
          preparedStatement.setString(1, nsForPlatformCiComponents);
          preparedStatement.setInt(2, sourceCmsCiRelationId);
          preparedStatement.setInt(3, fromCiId);
          preparedStatement.setInt(4, toCiId);

          log.info("preparedStatement: " + preparedStatement);
          int deletedRecordsCount = preparedStatement.executeUpdate();
          if (deletedRecordsCount != 0) {
            NumberOfCmsCiRelationsDeleted++;
            log.info(
                "CmsCiRelation deleted for nsForPlatformCiComponents <{}>, fromCiId <{}>, toCiId <{}>",
                nsForPlatformCiComponents, fromCiId, toCiId);
          } else {
            log.info(
                "ignored CmsCiRelation to delete for nsForPlatformCiComponents <{}>, fromCiId <{}>, toCiId <{}>",
                nsForPlatformCiComponents, fromCiId, toCiId);
          }

        }

      }

      log.info(
          "NumberOfCmsCiRelationsDeleted <{}>, for nsForPlatformCiComponents <{}>, fromClazz {}, toClazz {}, relationName {}",
          NumberOfCmsCiRelationsDeleted, nsForPlatformCiComponents, fromClazz, toClazz,
          relationName);
    } catch (Exception e) {
      throw new UnSupportedOperation("Error while fetching records" + e.getMessage());
    }

  }


  public int getNsIdForNsPath(String nsPath) {

    List<Integer> nsIds = new ArrayList<Integer>();
    try {


      String SQL_SELECT_nsIdForNsPath = "select * from ns_namespaces where ns_path=?";
      log.info("SQL_SELECT_nsIdForNsPath: " + SQL_SELECT_nsIdForNsPath);

      PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_nsIdForNsPath);
      preparedStatement.setString(1, nsPath);
      log.info("preparedStatement: " + preparedStatement);
      ResultSet resultSet = preparedStatement.executeQuery();

      int numberOfRecords = 0;
      while (resultSet.next()) {
        nsIds.add(resultSet.getInt("ns_id"));
        numberOfRecords++;

      }

      log.info("numberOfRecords {} for nsPath {} : nsIds {}", numberOfRecords, nsPath,
          nsIds.toString());
      if (numberOfRecords == 0 || numberOfRecords > 1) {
        throw new UnSupportedOperation(
            "numberOfRecords " + numberOfRecords + " invalid for nsPath :" + nsPath);
      }
    } catch (Exception e) {
      throw new UnSupportedOperation("Error while fetching records" + e.getMessage());
    }


    return nsIds.get(0);
  }


  public void cmsCi_update_ciClazzid_clazzname_goid(int ciId, String fromClazz, int fromClazzId,
      String toClazz, int toClazzId, String goid) {

    try {

      String SQL_UPDATE_CMSCI_ciClazzid_clazzname_goid =
          "UPDATE cm_ci set class_id=? , ci_goid=? , comments=?, updated_by=?, updated = now() where ci_id=?;";


      log.info("SQL_UPDATE_CMSCI_ciClazzid_clazzname_goid: "
          + SQL_UPDATE_CMSCI_ciClazzid_clazzname_goid);

      PreparedStatement preparedStatement =
          conn.prepareStatement(SQL_UPDATE_CMSCI_ciClazzid_clazzname_goid);

      preparedStatement.setInt(1, toClazzId);
      preparedStatement.setString(2, goid);
      preparedStatement.setString(3, IConstants.CIRCUIT_CONSOLIDATION_COMMENTS);
      preparedStatement.setString(4, IConstants.CIRCUIT_CONSOLIDATION_USER);
      preparedStatement.setInt(5, ciId);


      log.info("preparedStatement: " + preparedStatement);

      int numberOfCmsCiUpdatedForGoid = preparedStatement.executeUpdate();


      log.info(
          "numberOfCmsCiUpdatedForGoid <{}> for ciId <{}> fromClazz <{}> fromClazzId <{}> To toClazz <{}>, toClazzId <{}> with goid <{}>",
          numberOfCmsCiUpdatedForGoid, ciId, fromClazz, fromClazzId, toClazz, toClazzId, goid);


    } catch (Exception e) {
      throw new UnSupportedOperation("Error while fetching records" + e.getMessage());
    }

  }

  @Deprecated
  // Deprecated to allow new comments in new function
  public void switchCMSCIAttribuetId(String nsForPlatformCiComponents, int updatedTargetClazzId,
      int targetAttributeId, int sourceAttributeId) {

    String SQL_UPDATE_CMSCI_switchAttributeId =
        "UPDATE cm_ci_attributes set attribute_id=? where ci_attribute_id in "
            + "(SELECT ci_attribute_id from cm_ci_attributes ca, md_class_attributes cla, cm_ci ci, ns_namespaces ns "
            + "where " + "ca.ci_id=ci.ci_id " + "and ci.ns_id = ns.ns_id "
            + "and ca.attribute_id=cla.attribute_id " + "and ns.ns_path =? " + "and ci.class_id=? "
            + "and ca.attribute_id=?); ";


    try {

      log.info("SQL_UPDATE_CMSCI_switchAttributeId        : " + SQL_UPDATE_CMSCI_switchAttributeId);
      PreparedStatement preparedStatement =
          conn.prepareStatement(SQL_UPDATE_CMSCI_switchAttributeId);

      preparedStatement.setInt(1, targetAttributeId);
      preparedStatement.setString(2, nsForPlatformCiComponents);
      preparedStatement.setInt(3, updatedTargetClazzId);
      preparedStatement.setInt(4, sourceAttributeId);


      log.info("preparedStatement: " + preparedStatement);

      int numberOfUpdatedRecords = preparedStatement.executeUpdate();
      log.info("numberOfUpdatedRecords for SQL_UPDATE_CMSCI_switchAttributeId: {} ",
          numberOfUpdatedRecords);

    } catch (Exception e) {
      throw new UnSupportedOperation("Error while fetching records" + e.getMessage());
    }

  }

  public void deleteCMSCIAttribute(String nsForPlatformCiComponents, String sourceClazz,
      int sourceClazzId, int sourceClazzAttributeId, String sourceClazzAttributeName,
      String targetClazz, int targetClazzId) {


    log.info(
        "deleting CMSCIAttribute from attributes targetClazz <{}> targetClazzId <{}> sourceClazzAttributeId <{}> sourceClazzAttributeName <{}> belonging to sourceClazz <{}> sourceClazzId <{}>",
        targetClazz, targetClazzId, sourceClazzAttributeId, sourceClazzAttributeName, sourceClazz,
        sourceClazzId);


    String SQL_DELETE_CMSCIATTRIBUTE =
        "DELETE from cm_ci_attributes where ci_attribute_id in ( select ca.ci_attribute_id "
            + "from cm_ci_attributes ca, md_class_attributes cla, cm_ci ci, ns_namespaces ns "
            + "where " + "ca.ci_id=ci.ci_id " + "and ci.ns_id = ns.ns_id "
            + "and ca.attribute_id=cla.attribute_id " + "and ns.ns_path =? " + "and ci.class_id=? "
            + "and ca.attribute_id=?); ";


    try {

      log.info("SQL_DELETE_CMSCIATTRIBUTE        : " + SQL_DELETE_CMSCIATTRIBUTE);
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE_CMSCIATTRIBUTE);

      preparedStatement.setString(1, nsForPlatformCiComponents);
      preparedStatement.setInt(2, targetClazzId);
      preparedStatement.setInt(3, sourceClazzAttributeId);

      log.info("preparedStatement: " + preparedStatement);
      int numberOfRecords = preparedStatement.executeUpdate();

      log.info("Number of CMSCI attributes deleted: {}", numberOfRecords);

    } catch (Exception e) {
      throw new UnSupportedOperation("Error while fetching records" + e.getMessage());
    }



  }

  public int getNext_cm_pk_seqId() {

    try {

      String SQL_SELECT_NextCiId = "SELECT nextval('cm_pk_seq');";

      log.info("SQL_SELECT_NextCiId: " + SQL_SELECT_NextCiId);
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_NextCiId);

      log.info("preparedStatement: " + preparedStatement);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        int nextVal = resultSet.getInt("nextval");
        log.info("nextVal: " + nextVal);
        return nextVal;

      }
    } catch (Exception e) {
      throw new UnSupportedOperation("Error while fetching records" + e.getMessage());
    }
    throw new UnSupportedOperation("Unable to retrive next CiId");

  }

  public int getNext_dj_pk_seq() {

    try {

      String SQL_SELECT_Next_dj_pk_seq = "SELECT nextval('dj_pk_seq');";

      log.info("SQL_SELECT_Next_dj_pk_seq: " + SQL_SELECT_Next_dj_pk_seq);
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_Next_dj_pk_seq);

      log.info("preparedStatement: " + preparedStatement);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        int nextVal = resultSet.getInt("nextval");
        log.info("nextVal: " + nextVal);
        return nextVal;

      }
    } catch (Exception e) {
      throw new UnSupportedOperation("Error while fetching records" + e.getMessage());
    }
    throw new UnSupportedOperation("Unable to retrive next CiId");

  }



  public void createCMSCI(int nsId, int ciId, int targetClazzId, String ciName, String goid,
      int ciStateId, String comments, int last_applied_rfc_id, String createdBy) {

    /*
     * insert into cm_ci (ci_id, ns_id, class_id, ci_name, ci_goid, comments, ci_state_id,
     * last_applied_rfc_id, created_by) values (p_ci_id, p_ns_id, p_class_id, p_ci_name, p_goid,
     * p_comments, p_state_id, p_last_rfc_id, p_created_by);
     * 
     * insert into cms_ci_event_queue(event_id, source_pk, source_name, event_type_id) values
     * (nextval('event_pk_seq'), p_ci_id, 'cm_ci' , 100);
     * 
     * insert into cm_ci_log(log_id, log_time, log_event, ci_id, ci_name, class_id, class_name,
     * comments, ci_state_id, ci_state_id_old, created_by) values (nextval('log_pk_seq'), now(),
     * 100, p_ci_id, p_ci_name, p_class_id, l_class_name, p_comments, p_state_id, p_state_id,
     * p_created_by);
     */

    try {

      /*
       * String SQL_INSERT_CreateNewCMSCI =
       * "insert into cm_ci (ci_id, ns_id, class_id, ci_name, ci_goid, comments, ci_state_id, last_applied_rfc_id, created_by) "
       * +
       * "values (p_ci_id, p_ns_id, p_class_id, p_ci_name, p_goid, p_comments, p_state_id, p_last_rfc_id, p_created_by);"
       * ;
       */
      String SQL_INSERT_CreateNewCMSCI =
          "INSERT INTO cm_ci (ci_id, ns_id, class_id, ci_name, ci_goid, comments, ci_state_id, last_applied_rfc_id, created_by) "
              + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

      log.info("SQL_INSERT_CreateNewCMSCI: " + SQL_INSERT_CreateNewCMSCI);
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT_CreateNewCMSCI);

      preparedStatement.setInt(1, ciId);
      preparedStatement.setInt(2, nsId);
      preparedStatement.setInt(3, targetClazzId);
      preparedStatement.setString(4, ciName);
      preparedStatement.setString(5, goid);
      preparedStatement.setString(6, comments);
      preparedStatement.setInt(7, ciStateId);
      preparedStatement.setInt(8, last_applied_rfc_id);
      preparedStatement.setString(9, createdBy);

      log.info("preparedStatement: " + preparedStatement);

      int numberOfInserts = preparedStatement.executeUpdate();
      log.info("numberOfInserts: {}", numberOfInserts);


    } catch (Exception e) {
      throw new UnSupportedOperation("Error while creating new CMSCI " + e.getMessage());
    }

  }

  public void createNewCMSCIAttribute(int ci_attribute_id, int ci_id, int attribute_id,
      String targetDefaultValue, String owner, String comments) {


    // TODO Auto-generated method stub
    /*
     * insert into cm_ci_attributes (ci_attribute_id, ci_id, attribute_id, df_attribute_value,
     * dj_attribute_value, owner, comments) values (nextval('cm_pk_seq'), p_ci_id, p_attribute_id,
     * p_df_value, p_dj_value, p_owner, p_comments) returning ci_attribute_id into out_ci_attr_id;
     * 
     * insert into cm_ci_attribute_log(log_id, log_time, log_event, ci_id, ci_attribute_id,
     * attribute_id, attribute_name, comments, owner, dj_attribute_value, dj_attribute_value_old,
     * df_attribute_value, df_attribute_value_old) values (nextval('log_pk_seq'), now(), 100,
     * p_ci_id, out_ci_attr_id, p_attribute_id, l_attribute_name, p_comments, p_owner, p_dj_value,
     * p_dj_value, p_df_value, p_df_value);
     * 
     * if p_event = true then insert into cms_ci_event_queue(event_id, source_pk, source_name,
     * event_type_id) values (nextval('event_pk_seq'), p_ci_id, 'cm_ci' , 200);
     */

    log.info("Begin: createNewCMSCIAttribute ()");
    log.info("-------------------------------------------------------------------------");
    log.info("\n");
    try {

      String SQL_INSERT_AddNewCMSCIAttribute =
          "INSERT INTO cm_ci_attributes (ci_attribute_id, ci_id, attribute_id, df_attribute_value, dj_attribute_value, owner, comments) "
              + "VALUES (?, ?, ?, ?, ?, ?, ?);";

      log.info("SQL_INSERT_AddNewCMSCIAttribute: " + SQL_INSERT_AddNewCMSCIAttribute);
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT_AddNewCMSCIAttribute);

      preparedStatement.setInt(1, ci_attribute_id);
      preparedStatement.setInt(2, ci_id);
      preparedStatement.setInt(3, attribute_id);
      preparedStatement.setString(4, targetDefaultValue);
      preparedStatement.setString(5, targetDefaultValue);
      preparedStatement.setString(6, owner);
      preparedStatement.setString(7, comments);
      log.info("preparedStatement: " + preparedStatement);

      int numberOfRecords = preparedStatement.executeUpdate();
      log.info("numberOfRecords for SQL_INSERT_AddNewCMSCIAttribute {}", numberOfRecords);

    } catch (Exception e) {
      throw new UnSupportedOperation("Error while creating new CMSCI " + e.getMessage());
    }
    log.info("End: createNewCMSCIAttribute ()");
    log.info("-------------------------------------------------------------------------");
    log.info("\n");
  }

  public void createCMSCIRelation(int ci_relation_id, int ns_id, int from_ci_id,
      String relation_goid, int relation_id, int to_ci_id, int ci_state_id, String comments) {

    // {call cm_create_relation(#{ciRelationId}, #{nsId}, #{fromCiId}, #{relationId}, #{toCiId},
    // #{relationGoid}, #{comments}, #{relationStateId})}

    /*
     * insert into cm_ci_relations (ci_relation_id, ns_id, from_ci_id, relation_goid, relation_id,
     * to_ci_id, ci_state_id, comments, last_applied_rfc_id) values (p_ci_relation_id, p_ns_id,
     * p_from_ci_id, p_rel_goid, p_relation_id, p_to_ci_id, p_state_id, p_comments, p_last_rfc_id);
     * 
     * insert into cms_ci_event_queue(event_id, source_pk, source_name, event_type_id) values
     * (nextval('event_pk_seq'), p_ci_relation_id, 'cm_ci_rel' , 200);
     * 
     * insert into cm_ci_relation_log(log_id, log_time, log_event, ci_relation_id, from_ci_id,
     * to_ci_id, ci_state_id, ci_state_id_old, comments) values (nextval('log_pk_seq'), now(), 100,
     * p_ci_relation_id, p_from_ci_id, p_to_ci_id, p_state_id, p_state_id, p_comments); exception
     * when integrity_constraint_violation then raise notice '% %', sqlerrm, sqlstate;
     * 
     */


    try {

      String SQL_INSERT_CreateNewCMSIRelation =
          "insert into cm_ci_relations (ci_relation_id, ns_id, from_ci_id, relation_goid, relation_id, to_ci_id, ci_state_id, comments) "
              + "values (?, ?, ?, ?, ?, ?, ?, ?);";


      log.info("SQL_INSERT_CreateNewCMSIRelation: " + SQL_INSERT_CreateNewCMSIRelation);

      PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT_CreateNewCMSIRelation);

      preparedStatement.setInt(1, ci_relation_id);
      preparedStatement.setInt(2, ns_id);
      preparedStatement.setInt(3, from_ci_id);
      preparedStatement.setString(4, relation_goid);
      preparedStatement.setInt(5, relation_id);
      preparedStatement.setInt(6, to_ci_id);
      preparedStatement.setInt(7, ci_state_id);
      preparedStatement.setString(8, comments);


      log.info("preparedStatement: " + preparedStatement);

      int numberOfRecords = preparedStatement.executeUpdate();
      log.info("numberOfRecords for SQL_INSERT_CreateNewCMSIRelation {}", numberOfRecords);


    } catch (Exception e) {
      throw new UnSupportedOperation("Error while creating new CMSCIRelation " + e.getMessage());
    }


  }

  public void createCMSCIRelationAttribute(int ci_rel_attribute_id, int ci_relation_id,
      int attribute_id, String df_attribute_value, String dj_attribute_value, String owner,
      String comments) {

    // {call cm_add_ci_rel_attribute(#{ciRelationId}, #{attributeId}, #{dfValue}, #{djValue},
    // #{owner}, #{comments}, true)}

    /*
     * insert into cm_ci_relation_attributes (ci_rel_attribute_id, ci_relation_id, attribute_id,
     * df_attribute_value, dj_attribute_value, owner, comments) values (nextval('cm_pk_seq'),
     * p_ci_rel_id, p_attribute_id, p_df_value, p_dj_value, p_owner, p_comments) returning
     * ci_rel_attribute_id into out_ci_rel_attr_id;
     * 
     * insert into cm_ci_relation_attr_log(log_id, log_time, log_event, ci_relation_id,
     * ci_rel_attribute_id, attribute_id, attribute_name, comments, owner, dj_attribute_value,
     * dj_attribute_value_old, df_attribute_value, df_attribute_value_old) values
     * (nextval('log_pk_seq'), now(), 100, p_ci_rel_id, out_ci_rel_attr_id, p_attribute_id,
     * l_attribute_name, p_comments, p_owner, p_dj_value, p_dj_value, p_df_value, p_df_value);
     * 
     * if p_event = true then insert into cms_ci_event_queue(event_id, source_pk, source_name,
     * event_type_id) values (nextval('event_pk_seq'), p_ci_rel_id, 'cm_ci_rel' , 200); end if;
     */

    try {

      String SQL_INSERT_CreateNewCMSIRelationAttribute =
          "insert into cm_ci_relation_attributes (ci_rel_attribute_id, ci_relation_id, attribute_id, df_attribute_value, dj_attribute_value, owner, comments) "
              + "values (?, ?, ?, ?, ?, ?, ?);";


      log.info("SQL_INSERT_CreateNewCMSIRelationAttribute: "
          + SQL_INSERT_CreateNewCMSIRelationAttribute);

      PreparedStatement preparedStatement =
          conn.prepareStatement(SQL_INSERT_CreateNewCMSIRelationAttribute);

      preparedStatement.setInt(1, ci_rel_attribute_id);
      preparedStatement.setInt(2, ci_relation_id);
      preparedStatement.setInt(3, attribute_id);
      preparedStatement.setString(4, df_attribute_value);
      preparedStatement.setString(5, dj_attribute_value);
      preparedStatement.setString(6, owner);
      preparedStatement.setString(7, comments);

      log.info("preparedStatement: " + preparedStatement);
      int numberOfRecords = preparedStatement.executeUpdate();

      log.info("numberOfRecords for SQL_INSERT_CreateNewCMSIRelationAttribute {}", numberOfRecords);
    } catch (Exception e) {
      throw new UnSupportedOperation("Error while creating new CMSCIRelation " + e.getMessage());
    }


  }


  public List<Integer> getCMSCIRelationIds_By_Ns_RelationName_FromClazzToClazz(String ns_path,
      String relation_name, String fromClazz, String toClazz) {


    List<Integer> cmsCiRelationIds = new ArrayList<Integer>();
    try {

      String SQL_SELECT_CMSCIRelationIds_By_Ns_RelationName_FromClazzToClazz =
          "select cir.ci_relation_id from "
              + "cm_ci_relations cir, md_relations mdr, cm_ci_state cis, cm_ci from_ci, md_classes from_mdc, cm_ci to_ci, md_classes to_mdc, ns_namespaces ns"
              + " where ns.ns_path=?  and cir.ns_id = ns.ns_id "
              + " and cir.ci_state_id = cis.ci_state_id  and cir.relation_id = mdr.relation_id  "
              + " and mdr.relation_name = ?  and cir.from_ci_id = from_ci.ci_id "
              + " and from_ci.class_id = from_mdc.class_id   and from_mdc.class_name like ? "
              + " and cir.to_ci_id = to_ci.ci_id  and to_ci.class_id = to_mdc.class_id "
              + " and to_mdc.class_name like ? ";


      log.info("SQL_SELECT_CMSCIRelationIds_By_Ns_RelationName_FromClazzToClazz: "
          + SQL_SELECT_CMSCIRelationIds_By_Ns_RelationName_FromClazzToClazz);

      PreparedStatement preparedStatement =
          conn.prepareStatement(SQL_SELECT_CMSCIRelationIds_By_Ns_RelationName_FromClazzToClazz);

      preparedStatement.setString(1, ns_path);
      preparedStatement.setString(2, relation_name);
      preparedStatement.setString(3, fromClazz);
      preparedStatement.setString(4, toClazz);


      log.info("preparedStatement: " + preparedStatement);
      ResultSet resultSet = preparedStatement.executeQuery();

      int numberOfRecords = 0;
      while (resultSet.next()) {
        cmsCiRelationIds.add(resultSet.getInt("ci_relation_id"));
        numberOfRecords++;
      }
      log.info(
          "Number of CmsCiRelations: <{}> for ns_path <{}> relation_name <{}> fromClazz <{}> toClazz <{}>",
          numberOfRecords, ns_path, relation_name, fromClazz, toClazz);


    } catch (Exception e) {
      throw new UnSupportedOperation("Error while fetching records" + e.getMessage());
    }


    return cmsCiRelationIds;
  }


  // this method have potential to create a bug.
  // NsPath is not required when ciIds are available.
  // because of NsPath being supplied, the caller of the method will have to calculate nspath for
  // Design phase,
  // which will lead to unwanted coding efforts when ciIds are available
  // delete this method and replace by getCMSCIRelationIds_By_FromCiIdToCiId

  public List<Integer> getCMSCIRelationIds_By_Ns_RelationName_FromCiIdToCiId(String ns_path,
      String relation_name, int fromciId, int tociId) {


    List<Integer> cmsCiRelationIds = new ArrayList<Integer>();
    try {

      String SQL_SELECT_CMSCIRelationIds_By_Ns_RelationName_FromCiIdToCiId =
          "select cir.ci_relation_id , cir.from_ci_id , cir.to_ci_id "
              + "from cm_ci_relations cir, md_relations mdr, cm_ci_state cis, cm_ci from_ci, cm_ci to_ci, ns_namespaces ns "
              + "where ns.ns_path=?  and cir.ns_id = ns.ns_id  and cir.ci_state_id = cis.ci_state_id  "
              + "and cir.relation_id = mdr.relation_id   and mdr.relation_name = ?  and cir.from_ci_id = from_ci.ci_id  "
              + "and cir.to_ci_id = to_ci.ci_id and cir.from_ci_id=? and cir.to_ci_id=? ;";


      log.info("SQL_SELECT_CMSCIRelationIds_By_Ns_RelationName_FromCiIdToCiId: "
          + SQL_SELECT_CMSCIRelationIds_By_Ns_RelationName_FromCiIdToCiId);

      PreparedStatement preparedStatement =
          conn.prepareStatement(SQL_SELECT_CMSCIRelationIds_By_Ns_RelationName_FromCiIdToCiId);

      preparedStatement.setString(1, ns_path);
      preparedStatement.setString(2, relation_name);
      preparedStatement.setInt(3, fromciId);
      preparedStatement.setInt(4, tociId);


      log.info("preparedStatement: " + preparedStatement);
      ResultSet resultSet = preparedStatement.executeQuery();

      int numberOfRecords = 0;
      while (resultSet.next()) {
        cmsCiRelationIds.add(resultSet.getInt("ci_relation_id"));
        numberOfRecords++;
      }

      log.info(
          "Number of CmsCiRelations: <{}> for ns_path <{}> relation_name <{}> fromciId <{}> tociId <{}>",
          numberOfRecords, ns_path, relation_name, fromciId, tociId);

      if (numberOfRecords > 1) {
        throw new UnSupportedOperation(
            "numberOfRecords <" + numberOfRecords + "> Error: can not have more than 1 relation");
      }

    } catch (SQLException e) {
      throw new UnSupportedOperation("Error while fetching records" + e.getMessage());
    }


    return cmsCiRelationIds;
  }

  public List<Integer> getCMSCIRelationIds_By_RelationNameAndFromCiIdToCiId(String relation_name,
      int fromciId, int tociId) {


    List<Integer> cmsCiRelationIds = new ArrayList<Integer>();
    try {

      String SQL_SELECT_getCMSCIRelationIds_By_RelationNameAndFromCiIdToCiId =
          "select cir.ci_relation_id , cir.from_ci_id , cir.to_ci_id "
              + "from cm_ci_relations cir, md_relations mdr "
              + "where cir.relation_id = mdr.relation_id and mdr.relation_name = ? "
              + "and cir.from_ci_id=? and cir.to_ci_id=? ";


      log.info("SQL_SELECT_getCMSCIRelationIds_By_RelationNameAndFromCiIdToCiId: "
          + SQL_SELECT_getCMSCIRelationIds_By_RelationNameAndFromCiIdToCiId);

      PreparedStatement preparedStatement =
          conn.prepareStatement(SQL_SELECT_getCMSCIRelationIds_By_RelationNameAndFromCiIdToCiId);

      preparedStatement.setString(1, relation_name);
      preparedStatement.setInt(2, fromciId);
      preparedStatement.setInt(3, tociId);

      log.info("preparedStatement: " + preparedStatement);
      ResultSet resultSet = preparedStatement.executeQuery();

      int numberOfRecords = 0;
      while (resultSet.next()) {
        cmsCiRelationIds.add(resultSet.getInt("ci_relation_id"));
        numberOfRecords++;
      }

      log.info("Number of CmsCiRelations: <{}> for relation_name <{}> fromciId <{}> tociId <{}>",
          numberOfRecords, relation_name, fromciId, tociId);

      if (numberOfRecords > 1) {
        throw new UnSupportedOperation(
            "numberOfRecords <" + numberOfRecords + "> Error: can not have more than 1 relation");
      }

    } catch (SQLException e) {
      throw new UnSupportedOperation("Error while fetching records" + e.getMessage());
    }


    return cmsCiRelationIds;
  }

  public void updatePlatformSourceProperty(String ns, String platformName) {

    try {

      String SQL_UPDATE_PlatformSourceProperty = "UPDATE cm_ci_attributes "
          + " set df_attribute_value='oneops', dj_attribute_value='oneops' "
          + " where ci_attribute_id="
          + " (SELECT ca.ci_attribute_id from cm_ci_attributes ca, md_class_attributes cla, cm_ci ci, ns_namespaces ns "
          + " where ca.ci_id=ci.ci_id and ci.ns_id = ns.ns_id "
          + " and ca.attribute_id=cla.attribute_id " + " and cla.attribute_name='source' "
          + " and ns.ns_path =? " + " and ci.ci_name=?); ";


      log.info("SQL_UPDATE_PlatformSourceProperty: " + SQL_UPDATE_PlatformSourceProperty);

      PreparedStatement preparedStatement =
          conn.prepareStatement(SQL_UPDATE_PlatformSourceProperty);

      preparedStatement.setString(1, ns);
      preparedStatement.setString(2, platformName);


      log.info("preparedStatement: " + preparedStatement);
      int numberOfRecords = preparedStatement.executeUpdate();
      if (numberOfRecords != 1) {
        throw new UnSupportedOperation(
            "updatePlatformSourceProperty function should update 1 record however numberOfRecords= :"
                + numberOfRecords);
      }
      log.info("platform source updated to <oneops> for ns <{}> and platformName <{}>", ns,
          platformName);

    } catch (Exception e) {
      throw new UnSupportedOperation(
          "Error while processing SQL_UPDATE_PlatformSourceProperty" + e.getMessage());
    }

  }

  public String getCMSCIAttributeValueByAttribNameAndCiId(int ciId, int attributeId,
      String attributeName) {

    String SQL_SELECT_CMSCIATTRIBUTE_ValueByAttribNameAndCiId =
        "SELECT ca.df_attribute_value, ca.dj_attribute_value  "
            + "from cm_ci_attributes ca, md_class_attributes cla, cm_ci ci "
            + " where ca.ci_id=ci.ci_id " + " and ca.attribute_id=cla.attribute_id "
            + " and ci.ci_id=?" + " and ca.attribute_id=?" + " and cla.attribute_name=? ";


    try {

      log.info("SQL_SELECT_CMSCIATTRIBUTE_ValueByAttribNameAndCiId : "
          + SQL_SELECT_CMSCIATTRIBUTE_ValueByAttribNameAndCiId);
      PreparedStatement preparedStatement =
          conn.prepareStatement(SQL_SELECT_CMSCIATTRIBUTE_ValueByAttribNameAndCiId);

      preparedStatement.setInt(1, ciId);
      preparedStatement.setInt(2, attributeId);
      preparedStatement.setString(3, attributeName);

      log.info("preparedStatement: " + preparedStatement);
      ResultSet result = preparedStatement.executeQuery();

      String attributeValue = null;

      int numberOfRecords = 0;
      while (result.next()) {

        String df_attribute_value = result.getString("df_attribute_value");
        String dj_attribute_value = result.getString("dj_attribute_value");

        attributeValue = df_attribute_value;

        log.info(
            "ciId {}, attributeId {}, attributeName {}, df_attribute_value {} , dj_attribute_value {}"
                + ciId,
            attributeId, attributeName, df_attribute_value, dj_attribute_value);
        numberOfRecords++;

      }
      log.info("Number of CMSCI attribute values : {}", numberOfRecords);

      if (numberOfRecords > 1) {
        throw new UnSupportedOperation("numberOfRecords for ciId: " + ciId + ", attributeName: "
            + attributeName + " and attributeId" + attributeId + "not supported");
      }

      return attributeValue;

    } catch (Exception e) {
      throw new UnSupportedOperation("Error while fetching records" + e.getMessage());
    }


  }

  public Map<Integer, String> getCiIdsForNsClazzAndPlatformCiName(String ns, String clazz,
      String platformName) {

    Map<Integer, String> map = new HashMap<Integer, String>();
    //List<Integer> ciIds = new ArrayList<Integer>();

    try {

      String SQL_SELECT_PlatformCiIdsbyNsClazzAndPlatformName = "select " + "ci.ci_id as ciId, "
          + "ci.ci_name as ciName," + "ci.class_id as ciClassId," + "cl.class_name as ciClassName,"
          + "cl.impl as impl, " + "ci.ns_id as nsId, " + "ns.ns_path as nsPath, "
          + "ci.ci_goid as ciGoid, " + "ci.comments, " + "ci.ci_state_id as ciStateId, "
          + "st.state_name as ciState, " + "ci.last_applied_rfc_id as lastAppliedRfcId, "
          + "ci.created_by as createdBy, " + "ci.updated_by as updatedBy, " + "ci.created, "
          + "ci.updated " + "from cm_ci ci, md_classes cl, ns_namespaces ns, cm_ci_state st "
          + "where ns.ns_path = ? " + "and cl.class_name = ? " + "and ci.class_id = cl.class_id "
          + "and ci.ns_id = ns.ns_id " + "and ci.ci_state_id = st.ci_state_id and ci.ci_name=?;";

      log.info("SQL_SELECT_PlatformCiIdsbyNsClazzAndPlatformName : "
          + SQL_SELECT_PlatformCiIdsbyNsClazzAndPlatformName);
      PreparedStatement preparedStatement =
          conn.prepareStatement(SQL_SELECT_PlatformCiIdsbyNsClazzAndPlatformName);
      preparedStatement.setString(1, ns);
      preparedStatement.setString(2, clazz);
      preparedStatement.setString(3, platformName);

      log.info("preparedStatement: " + preparedStatement);
      ResultSet resultSet = preparedStatement.executeQuery();

      int numberOfRecords = 0;
      while (resultSet.next()) {
        int ciId=resultSet.getInt("ciId");
        String ciName=resultSet.getString("ciName");
        map.put(ciId, ciName);
        
        
        numberOfRecords++;

      }

      if (map.size() != 1) {
        log.info("platformName {} does not exists for ns {} and with clazzName ", platformName, ns,
            clazz);
        throw new UnSupportedOperation("UnSupported Platform Name");
      }
      log.info("numberOfRecords: " + numberOfRecords);
    } catch (Exception e) {
      throw new UnSupportedOperation("Error while fetching records" + e.getMessage());
    }
    return map;

  }

  public void switchCMSCIAttribuetId(String nsForPlatformCiComponents, int sourceAttributeId,
      String sourceAttributeName, int sourceClazzId, String sourceClazzname, int targetClassId,
      String targetClazzname, int targetAttributeId) {
    String SQL_UPDATE_CMSCI_switchAttributeId =
        "UPDATE cm_ci_attributes set attribute_id=? where ci_attribute_id in "
            + "(SELECT ci_attribute_id from cm_ci_attributes ca, md_class_attributes cla, cm_ci ci, ns_namespaces ns "
            + "where " + "ca.ci_id=ci.ci_id " + "and ci.ns_id = ns.ns_id "
            + "and ca.attribute_id=cla.attribute_id " + "and ns.ns_path =? " + "and ci.class_id=? "
            + "and ca.attribute_id=?); ";


    try {

      log.info(
          "Switching Attribute ID for attributeName <{}> from sourceAttributeId <{}> to targetAttributeId <{}>",
          sourceAttributeName, sourceAttributeId, targetAttributeId);
      log.info(
          "Switching Attribute ID belonging to sourceClazzId <{}>, sourceClazzname <{}> to targetClassId <{}> targetClazzname <{}>",
          sourceClazzId, sourceClazzname, targetClassId, targetClazzname);


      log.info("SQL_UPDATE_CMSCI_switchAttributeId        : " + SQL_UPDATE_CMSCI_switchAttributeId);
      PreparedStatement preparedStatement =
          conn.prepareStatement(SQL_UPDATE_CMSCI_switchAttributeId);

      preparedStatement.setInt(1, targetAttributeId);
      preparedStatement.setString(2, nsForPlatformCiComponents);
      preparedStatement.setInt(3, targetClassId);
      preparedStatement.setInt(4, sourceAttributeId);


      log.info("preparedStatement: " + preparedStatement);

      int numberOfUpdatedRecords = preparedStatement.executeUpdate();
      log.info("numberOfUpdatedRecords for SQL_UPDATE_CMSCI_switchAttributeId: {} ",
          numberOfUpdatedRecords);

    } catch (Exception e) {
      throw new UnSupportedOperation("Error while fetching records" + e.getMessage());
    }

  }



  /*
   * public void getCloudsForPlatformDeployment(String nsForPlatformCiComponents, String
   * platformName, String fromCiClazz, String toCiClazz, String relationName) {
   * 
   * try {
   * 
   * 
   * String SQL_SELECT_getCloudsForPlatformDeployment = " select " +
   * "cir.ci_relation_id as ciRelationId, cir.ns_id as nsId, " +
   * "ns.ns_path as nsPath, cir.from_ci_id as fromCiId, " +
   * "cir.relation_goid as relationGoid, cir.relation_id as relationId, " +
   * "mdr.relation_name as relationName, cir.to_ci_id as toCiId, " +
   * "cir.ci_state_id as relationStateId, cis.state_name as relationState, " +
   * "cir.last_applied_rfc_id as lastAppliedRfcId, cir.comments, cir.created, " + "cir.updated " +
   * "from cm_ci_relations cir, md_relations mdr, cm_ci_state cis, cm_ci from_ci, md_classes from_mdc, cm_ci to_ci, md_classes to_mdc, ns_namespaces ns "
   * + "where cir.ns_id = ns.ns_id and cir.ci_state_id = cis.ci_state_id " +
   * "and cir.relation_id = mdr.relation_id and cir.from_ci_id = from_ci.ci_id " +
   * "and from_ci.class_id = from_mdc.class_id and cir.to_ci_id = to_ci.ci_id " +
   * "and to_ci.class_id = to_mdc.class_id and ns.ns_path=? and mdr.relation_name=? " +
   * "and from_mdc.class_name=? and to_mdc.class_name=? ";
   * 
   * 
   * 
   * log.info("SQL_SELECT_getCloudsForPlatformDeployment: " +
   * SQL_SELECT_getCloudsForPlatformDeployment); PreparedStatement preparedStatement =
   * conn.prepareStatement(SQL_SELECT_getCloudsForPlatformDeployment);
   * preparedStatement.setString(1, nsForPlatformCiComponents); preparedStatement.setString(2,
   * relationName); preparedStatement.setString(3, fromCiClazz); preparedStatement.setString(4,
   * toCiClazz);
   * 
   * log.info("preparedStatement: " + preparedStatement); ResultSet result =
   * preparedStatement.executeQuery();
   * 
   * while(result.next()) {
   * 
   * log.info("result: " + result); log.info("toCiId: "+result.getInt("toCiId")); }
   * 
   * } catch (Exception e) { throw new UnSupportedOperation("Error while fetching records" +
   * e.getMessage()); }
   * 
   * }
   */

  public Map<String, Integer> getComputeCisDeployedInPlatformByNsPath(
      String nsForPlatformCiComponents) {

    try {

      Map<String, Integer> map = new HashMap<String, Integer>();


      String SQL_SELECT_getCloudsForPlatformDeployment =
          " select cir.ci_relation_id as ciRelationId, cir.ns_id as nsId, ns.ns_path as nsPath, cir.from_ci_id as fromCiId, "
              + "cir.relation_goid as relationGoid, cir.relation_id as relationId, "
              + "mdr.relation_name as relationName, cir.to_ci_id toCiId, to_ci.ci_name as toCiName, cir.ci_state_id as relationStateId, "
              + "cis.state_name as relationState, cir.last_applied_rfc_id as lastAppliedRfcId, cir.comments, cir.created, "
              + " cir.updated from cm_ci_relations cir, md_relations mdr, cm_ci_state cis, cm_ci from_ci, md_classes from_mdc, cm_ci to_ci, md_classes to_mdc, "
              + "ns_namespaces ns where cir.ns_id = ns.ns_id and cir.ci_state_id = cis.ci_state_id and cir.relation_id = mdr.relation_id and cir.from_ci_id = from_ci.ci_id "
              + "and from_ci.class_id = from_mdc.class_id and cir.to_ci_id = to_ci.ci_id and to_ci.class_id = to_mdc.class_id and "
              + "ns.ns_Path=? and  " + " mdr.relation_name='base.RealizedAs' and "
              + "from_mdc.class_name='manifest.Compute' and " + "to_mdc.class_name='bom.Compute' ";

      log.info("SQL_SELECT_getCloudsForPlatformDeployment: "
          + SQL_SELECT_getCloudsForPlatformDeployment);
      PreparedStatement preparedStatement =
          conn.prepareStatement(SQL_SELECT_getCloudsForPlatformDeployment);
      preparedStatement.setString(1, nsForPlatformCiComponents);

      log.info("preparedStatement: " + preparedStatement);
      ResultSet result = preparedStatement.executeQuery();

      while (result.next()) {


        log.info("computeCiName: {}, computeCi base.RealizedAs ciRelationId with cloud: {}"
            + result.getString("toCiName"), result.getInt("ciRelationId"));

        map.put(result.getString("toCiName"), result.getInt("ciRelationId"));

      }

      return map;

    } catch (Exception e) {
      throw new UnSupportedOperation("Error while fetching records" + e.getMessage());
    }

  }

  public String getClouidCiNameAndClazzByCiId(int cloudCiId) {
    String cloudCiName = new String();

    try {

      String SQL_SELECT_getClouidCiNameAndClazzByCiId = "select " + "ci.ci_id as ciId, "
          + "ci.ci_name as ciName," + "ci.class_id as ciClassId," + "cl.class_name as ciClassName,"
          + "cl.impl as impl, " + "ci.ns_id as nsId, " + "ns.ns_path as nsPath, "
          + "ci.ci_goid as ciGoid, " + "ci.comments, " + "ci.ci_state_id as ciStateId, "
          + "st.state_name as ciState, " + "ci.last_applied_rfc_id as lastAppliedRfcId, "
          + "ci.created_by as createdBy, " + "ci.updated_by as updatedBy, " + "ci.created, "
          + "ci.updated " + "from cm_ci ci, md_classes cl, ns_namespaces ns, cm_ci_state st "
          + "where ci.ci_id = ? " + "and cl.class_name = ? " + "and ci.class_id = cl.class_id "
          + "and ci.ns_id = ns.ns_id " + "and ci.ci_state_id = st.ci_state_id;";

      log.info(
          "SQL_SELECT_getClouidCiNameAndClazzByCiId : " + SQL_SELECT_getClouidCiNameAndClazzByCiId);

      PreparedStatement preparedStatement_SELECT_getClouidCiNameAndClazzByCiId =
          conn.prepareStatement(SQL_SELECT_getClouidCiNameAndClazzByCiId);
      preparedStatement_SELECT_getClouidCiNameAndClazzByCiId.setInt(1, cloudCiId);
      preparedStatement_SELECT_getClouidCiNameAndClazzByCiId.setString(2, "account.Cloud");



      log.info("preparedStatement_SELECT_getClouidCiNameAndClazzByCiId: "
          + preparedStatement_SELECT_getClouidCiNameAndClazzByCiId);
      ResultSet resultSet = preparedStatement_SELECT_getClouidCiNameAndClazzByCiId.executeQuery();

      int numberOfRecords = 0;
      while (resultSet.next()) {
        cloudCiName = resultSet.getString("ciName");
        numberOfRecords++;

      }

      log.info("numberOfRecords: " + numberOfRecords);

      if (numberOfRecords != 1) {
        throw new UnSupportedOperation(
            "numberOfRecords <" + numberOfRecords + "> for cloud ciId must be 1");
      }

    } catch (Exception e) {
      throw new UnSupportedOperation("Error while fetching records" + e.getMessage());
    }
    return cloudCiName;

  }

  public Map<String, Map<String, String>> getCMSCIRelationIdsMap_By_Ns_RelationName_FromClazzToClazz(
      String ns_path, String relation_name, String fromClazz, String toClazz) {

    Map<String, Map<String, String>> cmsCiRelationIdsMap =
        new HashMap<String, Map<String, String>>();
    try {

      String SQL_SELECT_cmsCiRelationIdsMap_By_Ns_RelationName_FromClazzToClazz =
          "select cir.ci_relation_id, from_ci.ci_id fromCiId, from_ci.ci_name fromCiName, to_ci.ci_id toCiId, to_ci.ci_name toCiName from"
              + " cm_ci_relations cir, md_relations mdr, cm_ci_state cis, cm_ci from_ci, md_classes from_mdc, cm_ci to_ci, md_classes to_mdc, ns_namespaces ns"
              + " where ns.ns_path=?  and cir.ns_id = ns.ns_id "
              + " and cir.ci_state_id = cis.ci_state_id  and cir.relation_id = mdr.relation_id  "
              + " and mdr.relation_name = ?  and cir.from_ci_id = from_ci.ci_id "
              + " and from_ci.class_id = from_mdc.class_id   and from_mdc.class_name = ? "
              + " and cir.to_ci_id = to_ci.ci_id  and to_ci.class_id = to_mdc.class_id "
              + " and to_mdc.class_name = ? ";


      log.info("SQL_SELECT_cmsCiRelationIdsMap_By_Ns_RelationName_FromClazzToClazz: "
          + SQL_SELECT_cmsCiRelationIdsMap_By_Ns_RelationName_FromClazzToClazz);

      PreparedStatement preparedStatement =
          conn.prepareStatement(SQL_SELECT_cmsCiRelationIdsMap_By_Ns_RelationName_FromClazzToClazz);

      preparedStatement.setString(1, ns_path);
      preparedStatement.setString(2, relation_name);
      preparedStatement.setString(3, fromClazz);
      preparedStatement.setString(4, toClazz);


      log.info("preparedStatement: " + preparedStatement);
      ResultSet resultSet = preparedStatement.executeQuery();

      int numberOfRecords = 0;
      while (resultSet.next()) {
        String ci_relation_id = resultSet.getString("ci_relation_id");
        String fromCiId = resultSet.getString("fromCiId");
        String fromCiName = resultSet.getString("fromCiName");
        String toCiId = resultSet.getString("toCiId");
        String toCiName = resultSet.getString("toCiName");

        Map<String, String> map = new HashMap<String, String>();

        map.put("ci_relation_id", ci_relation_id);
        map.put("fromCiId", fromCiId);
        map.put("fromCiName", fromCiName);
        map.put("toCiId", toCiId);
        map.put("toCiName", toCiName);

        String key = fromCiName + "|" + relation_name + "|" + toCiName;
        log.info("ci_relation_id {} key: {}", ci_relation_id, key);
        cmsCiRelationIdsMap.put(key, map);

        numberOfRecords++;
      }
      log.info(
          "Number of CmsCiRelations: <{}> for ns_path <{}> relation_name <{}> fromClazz <{}> toClazz <{}>",
          numberOfRecords, ns_path, relation_name, fromClazz, toClazz);


    } catch (Exception e) {
      throw new UnSupportedOperation("Error while fetching records" + e.getMessage());
    }


    return cmsCiRelationIdsMap;

  }

  public String getCIRelationAttrValueByCiRelIdAndAttribName(int ci_relation_id,
      String attribute_name) {

    try {

      String SQL_SELECT_getCIRelationAttrByCiRelIdAndAttribName =
          "select cira.ci_rel_attribute_id as ciRelationAttributeId,"
              + "            cira.ci_relation_id as ciRelationId,"
              + "            cira.attribute_id as attributeId,"
              + "            mra.attribute_name as attributeName,"
              + "            cira.df_attribute_value as dfValue,"
              + "            cira.dj_attribute_value as djValue, cira.owner,"
              + "            cira.comments, cira.created, cira.updated "
              + "        from cm_ci_relation_attributes cira, md_relation_attributes mra "
              + "        where cira.ci_relation_id = ?"
              + "            and cira.attribute_id = mra.attribute_id"
              + "            and mra.attribute_name=?";


      log.info("SQL_SELECT_getCIRelationAttrByCiRelIdAndAttribName: "
          + SQL_SELECT_getCIRelationAttrByCiRelIdAndAttribName);

      PreparedStatement preparedStatement =
          conn.prepareStatement(SQL_SELECT_getCIRelationAttrByCiRelIdAndAttribName);

      preparedStatement.setInt(1, ci_relation_id);
      preparedStatement.setString(2, attribute_name);

      log.info("preparedStatement: " + preparedStatement);
      ResultSet result = preparedStatement.executeQuery();

      int numberOfRecords = 0;
      String attribValue = new String();
      while (result.next()) {
        attribValue = result.getString("dfValue");
        numberOfRecords++;

      }

      if (numberOfRecords != 1) {
        log.error("There must be an attribute value for ci_relation_id {} & attribute_name {}",
            ci_relation_id, attribute_name);
        throw new UnSupportedOperation(
            "numberOfRecords for  SQL_SELECT_getCIRelationAttrByCiRelIdAndAttribName must be 1"
                + numberOfRecords);
      }


      return attribValue;


    } catch (Exception e) {
      log.error("Error while fetching records for ci_relation_id {} & attribute_name {}",
          ci_relation_id, attribute_name);

      throw new UnSupportedOperation(
          "Error while processing SQL_SELECT_getCIRelationAttrByCiRelIdAndAttribName "
              + e.getMessage());
    }


  }

  public void create_dj_rfc_ci(int rfc_id, int release_id, int ci_id, int ns_id, int class_id,
      String ci_name, String ci_goid, int action_id, String created_by, String updated_by,
      String comments) {

    try {

      String SQL_INSERT_create_dj_rfc_ci =
          "INSERT INTO kloopzcm.dj_rfc_ci (rfc_id, release_id, ci_id, ns_id, class_id, "
              + " ci_name, ci_goid, action_id, created_by, updated_by, comments) "
              + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

      log.info("SQL_INSERT_create_dj_rfc_ci: " + SQL_INSERT_create_dj_rfc_ci);
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT_create_dj_rfc_ci);

      preparedStatement.setInt(1, rfc_id);
      preparedStatement.setInt(2, release_id);
      preparedStatement.setInt(3, ci_id);
      preparedStatement.setInt(4, ns_id);
      preparedStatement.setInt(5, class_id);
      preparedStatement.setString(6, ci_name);
      preparedStatement.setString(7, ci_goid);
      preparedStatement.setInt(8, action_id);
      preparedStatement.setString(9, created_by);
      preparedStatement.setString(10, updated_by);
      preparedStatement.setString(11, comments);

      log.info("preparedStatement: " + preparedStatement);

      int numberOfInserts = preparedStatement.executeUpdate();
      log.info("numberOfInserts: {}", numberOfInserts);


    } catch (Exception e) {
      throw new UnSupportedOperation("Error while creating new dj_rfc_ci " + e.getMessage());
    }

  }

  public void create_dj_release(int release_id, int ns_id, int parent_release_id,
      String release_name, String created_by, String commited_by, int release_state_id,
      String release_type, String description, int revision) {
    try {

      String SQL_INSERT_create_dj_release =
          "INSERT INTO kloopzcm.dj_releases (release_id, ns_id, parent_release_id, release_name, created_by, commited_by, "
              + " release_state_id, release_type, description, revision) "
              + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

      log.info("SQL_INSERT_create_dj_release: " + SQL_INSERT_create_dj_release);
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT_create_dj_release);



      preparedStatement.setInt(1, release_id);
      preparedStatement.setInt(2, ns_id);
      preparedStatement.setInt(3, parent_release_id);
      preparedStatement.setString(4, release_name);
      preparedStatement.setString(5, created_by);
      preparedStatement.setString(6, commited_by);
      preparedStatement.setInt(7, release_state_id);
      preparedStatement.setString(8, release_type);
      preparedStatement.setString(9, description);
      preparedStatement.setInt(10, revision);

      log.info("preparedStatement: " + preparedStatement);

      int numberOfInserts = preparedStatement.executeUpdate();
      log.info("numberOfInserts: {}", numberOfInserts);


    } catch (Exception e) {
      throw new UnSupportedOperation("Error while creating new dj_rfc_ci " + e.getMessage());
    }

  }

  public int getLastAppliedDjReleaseIdForNsReleasePath(String releaseNsPath) {

    int lastAppliedReleaseId = 0;
    try {

      String SQL_SELECT_getLastAppliedDjReleaseIdForNsReleasePath =
          "select r.release_id as releaseId, r.ns_id as nsId, "
              + " r.parent_release_id as parentReleaseId, "
              + " ns.ns_path as nsPath, r.release_name as releaseName, "
              + " r.created_by as createdBy, r.commited_by as commitedBy, "
              + " r.release_state_id as releaseStateId, rs.state_name as releaseState, "
              + " r.release_type as releaseType, r.description, "
              + " r.revision, r.created, r.updated "
              + " from dj_releases r, ns_namespaces ns, dj_release_states rs "
              + " where r.ns_id=ns.ns_id and r.release_state_id=rs.release_state_id and "
              + " (rs.state_name='closed' or rs.state_name='canceled') and "
              + " ns.ns_path=? order by r.release_id desc;";

      log.info("SQL_SELECT_getLastAppliedDjReleaseIdForNsReleasePath: "
          + SQL_SELECT_getLastAppliedDjReleaseIdForNsReleasePath);

      PreparedStatement preparedStatement =
          conn.prepareStatement(SQL_SELECT_getLastAppliedDjReleaseIdForNsReleasePath);
      preparedStatement.setString(1, releaseNsPath);
      log.info("preparedStatement: " + preparedStatement);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        lastAppliedReleaseId = resultSet.getInt("releaseId");
        return lastAppliedReleaseId;

      }

    } catch (SQLException e) {
      throw new UnSupportedOperation("Error while fetching records: " + e.getMessage());
    }

    return lastAppliedReleaseId;
  }


  // Method used by cleanup process
  public void updateCMSCiRelationAttributeValue_by_CiRelationIdAndAttributeName(int ci_relation_id,
      String attribute_name, String expectedAttributeValue) {

    try {

      String SQL_UPDATE_updateCMSCiRelationAttributeValue_by_CiRelationIdAndAttributeName =
          "update cm_ci_relation_attributes " + "set df_attribute_value=? , dj_attribute_value =? "
              + " where ci_rel_attribute_id = (select cira.ci_rel_attribute_id from cm_ci_relation_attributes cira, "
              + " md_relation_attributes mra where cira.ci_relation_id = ? and cira.attribute_id = mra.attribute_id  and mra.attribute_name=?);";


      log.info("SQL_UPDATE_updateCMSCiRelationAttributeValue_by_CiRelationIdAndAttributeName: "
          + SQL_UPDATE_updateCMSCiRelationAttributeValue_by_CiRelationIdAndAttributeName);

      PreparedStatement preparedStatement = conn.prepareStatement(
          SQL_UPDATE_updateCMSCiRelationAttributeValue_by_CiRelationIdAndAttributeName);

      preparedStatement.setString(1, expectedAttributeValue);
      preparedStatement.setString(2, expectedAttributeValue);
      preparedStatement.setInt(3, ci_relation_id);
      preparedStatement.setString(4, attribute_name);

      log.info("preparedStatement: " + preparedStatement);
      int numberOfUpdatedRecords = preparedStatement.executeUpdate();

      if (numberOfUpdatedRecords != 1) {
        log.error(
            "There must be one and only one row update for ci_relation_id {} & attribute_name {}",
            ci_relation_id, attribute_name);
        throw new UnSupportedOperation(
            "numberOfRecords for  SQL_UPDATE_updateCMSCiRelationAttributeValue_by_CiRelationIdAndAttributeName must be 1, "
                + numberOfUpdatedRecords);
      }



    } catch (Exception e) {
      log.error("Error while fetching records for ci_relation_id {} & attribute_name {}",
          ci_relation_id, attribute_name);

      throw new UnSupportedOperation(
          "Error while processing SQL_UPDATE_updateCMSCiRelationAttributeValue_by_CiRelationIdAndAttributeName "
              + e.getMessage());
    }


  }

  public Map<Integer, Map<String, String>> getCmsCiRelationAttributeIdsAndValuesMap(
      int ci_relation_id) {


    Map<Integer, Map<String, String>> cmsCiRelationAttributeIdsAndValuesMap =
        new HashMap<Integer, Map<String, String>>();
    try {

      String SQL_SELECT_getCmsCiRelationAttributeIdsAndValuesMap =
          "select cira.ci_rel_attribute_id as ciRelationAttributeId,"
              + "            cira.ci_relation_id as ciRelationId,"
              + "            cira.attribute_id as attributeId,"
              + "            mra.attribute_name as attributeName,"
              + "            cira.df_attribute_value as dfValue,"
              + "            cira.dj_attribute_value as djValue, cira.owner,"
              + "            cira.comments, cira.created, cira.updated "
              + "        from cm_ci_relation_attributes cira, md_relation_attributes mra "
              + "        where cira.ci_relation_id = ?"
              + "            and cira.attribute_id = mra.attribute_id;";


      log.info("SQL_SELECT_getCmsCiRelationAttributeIdsAndValuesMap: "
          + SQL_SELECT_getCmsCiRelationAttributeIdsAndValuesMap);

      PreparedStatement preparedStatement =
          conn.prepareStatement(SQL_SELECT_getCmsCiRelationAttributeIdsAndValuesMap);

      preparedStatement.setInt(1, ci_relation_id);

      log.info("preparedStatement: " + preparedStatement);
      ResultSet result = preparedStatement.executeQuery();

      int numberOfRecords = 0;

      while (result.next()) {
        int attribId = result.getInt("ciRelationAttributeId");

        Map<String, String> map = new HashMap<>();
        map.put("ci_rel_attribute_id", result.getString("ciRelationAttributeId"));
        map.put("ci_relation_id", result.getString("ciRelationId"));
        map.put("attribute_id", result.getString("attributeId"));
        map.put("attribute_name", result.getString("attributeName"));
        map.put("df_attribute_value", result.getString("dfValue"));
        map.put("dj_attribute_value", result.getString("djValue"));


        cmsCiRelationAttributeIdsAndValuesMap.put(attribId, map);

        numberOfRecords++;

      }

      log.info("numberOfRecords {}", numberOfRecords);
      return cmsCiRelationAttributeIdsAndValuesMap;


    } catch (Exception e) {
      log.error("Error while fetching records for ci_relation_id {} & attribute_name {}",
          ci_relation_id);

      throw new UnSupportedOperation(
          "Error while processing SQL_SELECT_getCIRelationAttrByCiRelIdAndAttribName "
              + e.getMessage());
    }
  }

  public void deleteCmsCiRelationAttribute_by_cmsCiRelationAttributeId(
      int cmsCiRelationCiAttribId) {


    try {

      String SQL_DELETE_deleteCmsCiRelationAttribute_by_cmsCiRelationAttributeId =
          "DELETE from cm_ci_relation_attributes where ci_rel_attribute_id =?;";

      log.info("SQL_DELETE_deleteCmsCiRelationAttribute_by_cmsCiRelationAttributeId: "
          + SQL_DELETE_deleteCmsCiRelationAttribute_by_cmsCiRelationAttributeId);

      PreparedStatement preparedStatement = conn
          .prepareStatement(SQL_DELETE_deleteCmsCiRelationAttribute_by_cmsCiRelationAttributeId);

      preparedStatement.setInt(1, cmsCiRelationCiAttribId);

      log.info("preparedStatement: " + preparedStatement);
      int numberOfUpdatedRecords = preparedStatement.executeUpdate();

      if (numberOfUpdatedRecords != 1) {
        log.error("There must be one and only one row update for cmsCiRelationCiAttribId {} ",
            cmsCiRelationCiAttribId);
        throw new UnSupportedOperation(
            "numberOfRecords for  SQL_UPDATE_updateCMSCiRelationAttributeValue_by_CiRelationIdAndAttributeName must be 1, "
                + numberOfUpdatedRecords);
      }


    } catch (Exception e) {
      log.error("Error while deleting records for cmsCiRelationCiAttribId {}",
          cmsCiRelationCiAttribId);

      throw new UnSupportedOperation(
          "Error while processing SQL_UPDATE_updateCMSCiRelationAttributeValue_by_CiRelationIdAndAttributeName "
              + e.getMessage());
    }


  }

  // used for monitors cleanup
  public void deleteCmsCibyNsAndCiName(String nsForPlatformCiComponents, String monitorCiName) {
    try {

      String SQL_deleteMonitrCmsCiForNsAndClazz = "DELETE from cm_ci where ci_id in "
          + "( SELECT ci.ci_id FROM cm_ci ci, md_classes cl, ns_namespaces ns, cm_ci_state st "
          + "where ns.ns_path = ? " + "and ci.ci_name = ?  and ci.class_id = cl.class_id "
          + "and ci.ns_id = ns.ns_id " + "and ci.ci_state_id = st.ci_state_id );";

      log.info("SQL_deleteMonitrCmsCiForNsAndClazz: " + SQL_deleteMonitrCmsCiForNsAndClazz);
      PreparedStatement preparedStatement =
          conn.prepareStatement(SQL_deleteMonitrCmsCiForNsAndClazz);
      preparedStatement.setString(1, nsForPlatformCiComponents);
      preparedStatement.setString(2, monitorCiName);
      log.info("preparedStatement: " + preparedStatement);
      int NumberOfMonitorCmsCiDeleted = preparedStatement.executeUpdate();
      log.info("NumberOfMonitorCmsCiDeleted <{}> for nsForPlatformCiComponents <{}> and clazz <{}>",
          NumberOfMonitorCmsCiDeleted, nsForPlatformCiComponents, monitorCiName);

      if (NumberOfMonitorCmsCiDeleted != 1) {

        throw new UnSupportedOperation("NumberOfMonitorCmsCiDeleted for nsForPlatformCiComponents <"
            + nsForPlatformCiComponents + "> for monitorCiName <" + monitorCiName + "> is <"
            + NumberOfMonitorCmsCiDeleted + "> , it should be 1");

      }

    } catch (Exception e) {
      throw new UnSupportedOperation("Error while fetching records" + e.getMessage());
    }

  }

  public int getClazzIdForClazzName(String monitorCmsCiClazzName) {


    int clazzId = 0;
    try {

      String SQL_SELECT_getClazzIdForClazzName = "select * from md_classes where class_name=?";

      log.info("SQL_SELECT_getClazzIdForClazzName : " + SQL_SELECT_getClazzIdForClazzName);
      PreparedStatement preparedStatement =
          conn.prepareStatement(SQL_SELECT_getClazzIdForClazzName);
      preparedStatement.setString(1, monitorCmsCiClazzName);

      log.info("preparedStatement: " + preparedStatement);
      ResultSet resultSet = preparedStatement.executeQuery();

      int numberOfRecords = 0;
      while (resultSet.next()) {
        clazzId = resultSet.getInt("class_id");
        numberOfRecords++;

      }

      log.info("numberOfRecords: " + numberOfRecords);
      if (numberOfRecords != 1) {
        throw new UnSupportedOperation("Number of clazzIds must be 1 for given clazzName");
      }
      return clazzId;
    } catch (Exception e) {
      throw new UnSupportedOperation("Error while fetching records" + e.getMessage());
    }

  }

  public int getMdRelationIdForMdRelationName(String mdRelationName) {

    int mdRelationId = 0;
    try {

      String SQL_SELECT_getClazzIdForClazzName = "select * from md_relations where relation_name=?";

      log.info("SQL_SELECT_getClazzIdForClazzName : " + SQL_SELECT_getClazzIdForClazzName);
      PreparedStatement preparedStatement =
          conn.prepareStatement(SQL_SELECT_getClazzIdForClazzName);
      preparedStatement.setString(1, mdRelationName);

      log.info("preparedStatement: " + preparedStatement);
      ResultSet resultSet = preparedStatement.executeQuery();

      int numberOfRecords = 0;
      while (resultSet.next()) {
        mdRelationId = resultSet.getInt("relation_id");
        numberOfRecords++;

      }

      log.info("numberOfRecords: " + numberOfRecords);
      if (numberOfRecords != 1) {
        throw new UnSupportedOperation("Number of clazzIds must be 1 for given clazzName");
      }
      return mdRelationId;
    } catch (Exception e) {
      throw new UnSupportedOperation("Error while fetching records" + e.getMessage());
    }
  }



}
