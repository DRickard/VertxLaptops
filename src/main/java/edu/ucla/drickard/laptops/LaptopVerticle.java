package edu.ucla.drickard.laptops;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLConnection;

import java.util.List;
import java.util.stream.Collectors;

public class LaptopVerticle extends AbstractVerticle {
  @Override
  public void start(Future<Void> fut) {
    JsonObject config = new JsonObject()
      .put("url","db-url")
      .put("driver_class", "oracle.jdbc.OracleDriver")
      .put("user", "db_user")
      .put("password", "db_pass");
    JDBCClient jdbc = JDBCClient.createShared(vertx, config);
    jdbc.getConnection(ar -> {
      if (ar.failed()) {
        fut.fail(ar.cause());
      } else {
        SQLConnection connection = ar.result();
        connection.query("SELECT loc, chromebooks_in, mac_laptops_in, win_laptops_in, ipads_in FROM vger_support.alma_counts ORDER By loc", query -> {
          List<AvailableItems> items = query.result().getRows().stream().map(AvailableItems::new).collect(Collectors.toList());
	  JsonObject output = new JsonObject().put("items", items);
          vertx
            .createHttpServer()
            .requestHandler(r -> {
              r.response().putHeader("content-type", "application/json; charset=utf-8")
		          .end(output.encodePrettily());
	      connection.close();
            })
            .listen(8080, result -> {
              if (result.succeeded()) {
                fut.complete();
              } else {
                fut.fail(result.cause());
              }
            });
        });
      }
    });
  }
}
