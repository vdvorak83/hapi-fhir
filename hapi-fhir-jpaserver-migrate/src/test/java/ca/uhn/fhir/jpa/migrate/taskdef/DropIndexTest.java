package ca.uhn.fhir.jpa.migrate.taskdef;

import ca.uhn.fhir.jpa.migrate.JdbcUtils;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class DropIndexTest extends BaseTest {


	@Test
	public void testIndexAlreadyExists() throws SQLException {
		executeSql("create table SOMETABLE (PID bigint not null, TEXTCOL varchar(255))");
		executeSql("create index IDX_ANINDEX on SOMETABLE (PID, TEXTCOL)");
		executeSql("create index IDX_DIFINDEX on SOMETABLE (TEXTCOL)");

		DropIndexTask task = new DropIndexTask("1", "1");
		task.setDescription("Drop an index");
		task.setIndexName("IDX_ANINDEX");
		task.setTableName("SOMETABLE");
		getMigrator().addTask(task);

		getMigrator().migrate();

		assertThat(JdbcUtils.getIndexNames(getConnectionProperties(), "SOMETABLE"), contains("IDX_DIFINDEX"));
	}

	@Test
	public void testIndexDoesntAlreadyExist() throws SQLException {
		executeSql("create table SOMETABLE (PID bigint not null, TEXTCOL varchar(255))");
		executeSql("create index IDX_DIFINDEX on SOMETABLE (TEXTCOL)");

		DropIndexTask task = new DropIndexTask("1", "1");
		task.setDescription("Drop an index");
		task.setIndexName("IDX_ANINDEX");
		task.setTableName("SOMETABLE");
		getMigrator().addTask(task);

		getMigrator().migrate();

		assertThat(JdbcUtils.getIndexNames(getConnectionProperties(), "SOMETABLE"), contains("IDX_DIFINDEX"));
	}


	@Test
	public void testConstraintAlreadyExists() throws SQLException {
		executeSql("create table SOMETABLE (PID bigint not null, TEXTCOL varchar(255))");
		executeSql("create index IDX_ANINDEX on SOMETABLE (PID, TEXTCOL)");
		executeSql("create index IDX_DIFINDEX on SOMETABLE (TEXTCOL)");

		DropIndexTask task = new DropIndexTask("1", "1");
		task.setDescription("Drop an index");
		task.setIndexName("IDX_ANINDEX");
		task.setTableName("SOMETABLE");
		getMigrator().addTask(task);

		getMigrator().migrate();

		assertThat(JdbcUtils.getIndexNames(getConnectionProperties(), "SOMETABLE"), contains("IDX_DIFINDEX"));
	}

	@Test
	public void testConstraintDoesntAlreadyExist() throws SQLException {
		executeSql("create table SOMETABLE (PID bigint not null, TEXTCOL varchar(255))");
		executeSql("create index IDX_DIFINDEX on SOMETABLE (TEXTCOL)");

		DropIndexTask task = new DropIndexTask("1", "1");
		task.setDescription("Drop an index");
		task.setIndexName("IDX_ANINDEX");
		task.setTableName("SOMETABLE");
		getMigrator().addTask(task);

		getMigrator().migrate();

		assertThat(JdbcUtils.getIndexNames(getConnectionProperties(), "SOMETABLE"), contains("IDX_DIFINDEX"));
	}

}
