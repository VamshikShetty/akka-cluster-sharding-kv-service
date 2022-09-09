# Akka versions.
akka_versions_ScalaBinary = "2_13"

java_binary(
   name = "kv-akka-sharded",
   main_class = "com.example.akka.sharding.kv.spring.OpenApiGeneratorApplication",
   srcs = glob(["src/**/*.java"]),
   resources = glob(["src/main/resources/**"]),
   deps = [
      # dependency to use tuple pair
      "@maven//:org_apache_commons_commons_lang3",

      # Logging dependencies
      "@maven//:ch_qos_logback_logback_classic",
      "@maven//:org_slf4j_slf4j_api",
      "@maven//:org_slf4j_slf4j_log4j12",

      # Jackson serialization dependencies
      "@maven//:com_fasterxml_jackson_core_jackson_annotations",
      "@maven//:com_fasterxml_jackson_core_jackson_databind",
      "@maven//:com_fasterxml_jackson_core_jackson_core",
      "@maven//:com_fasterxml_jackson_module_jackson_module_scala_{}".format(akka_versions_ScalaBinary),

      # GSON
      "@maven//:com_google_code_gson_gson",

      # Springframework dependencies.
      "@maven//:javax_servlet_javax_servlet_api",
      "@maven//:org_springframework_boot_spring_boot",
      "@maven//:org_springframework_boot_spring_boot_test",
      "@maven//:org_springframework_boot_spring_boot_autoconfigure",
      "@maven//:org_springframework_boot_spring_boot_starter_web",
      "@maven//:org_springframework_boot_spring_boot_starter_validation",
      "@maven//:org_springframework_boot_spring_boot_starter_test",
      "@maven//:org_springframework_spring_beans",
      "@maven//:org_springframework_spring_context",
      "@maven//:org_springframework_spring_web",

      "@maven//:org_openapitools_jackson_databind_nullable",
      "@maven//:org_junit_jupiter_junit_jupiter_api",

      # Akka remoting dependencies
      "@maven//:com_typesafe_akka_akka_remote_2_13",
      "@maven//:com_typesafe_akka_akka_stream_2_13",

      # Akka actor dependencies
      "@maven//:com_typesafe_akka_akka_actor_{}".format(akka_versions_ScalaBinary),
      "@maven//:com_typesafe_akka_akka_actor_typed_{}".format(akka_versions_ScalaBinary),
      "@maven//:com_typesafe_akka_akka_serialization_jackson_{}".format(akka_versions_ScalaBinary),

      # Akka cluster dependencies
      "@maven//:com_typesafe_akka_akka_cluster_sharding_{}".format(akka_versions_ScalaBinary),
      "@maven//:com_typesafe_akka_akka_cluster_sharding_typed_{}".format(akka_versions_ScalaBinary),
      "@maven//:com_typesafe_akka_akka_cluster_typed_{}".format(akka_versions_ScalaBinary),
      "@maven//:com_typesafe_akka_akka_cluster_{}".format(akka_versions_ScalaBinary),

      # Persistence dependencies
      "@maven//:com_typesafe_akka_akka_persistence_typed_{}".format(akka_versions_ScalaBinary),

      # Cassandra dependencies
      "@maven//:com_typesafe_akka_akka_persistence_cassandra_{}".format(akka_versions_ScalaBinary),
      "@maven//:com_typesafe_akka_akka_persistence_query_{}".format(akka_versions_ScalaBinary),
      "@maven//:com_typesafe_akka_akka_cluster_tools_{}".format(akka_versions_ScalaBinary),

      # Cassandra datastax driver queries dependencies
      "@maven//:com_datastax_oss_java_driver_core",

      # Alpakka dependencies
      "@maven//:com_lightbend_akka_akka_stream_alpakka_cassandra_{}".format(akka_versions_ScalaBinary),

      # Map maker for cache entries with, ref : https://www.baeldung.com/guava-mapmaker
      "@maven//:com_google_guava_guava",

      "@maven//:io_swagger_core_v3_swagger_annotations",
      "@maven//:io_swagger_core_v3_swagger_models",

      "@maven//:javax_validation_validation_api",
      "@maven//:javax_annotation_javax_annotation_api",
   ],
)

# lombok has problem generating code based on annotations for bazel.
# ref : https://github.com/bazelbuild/bazel/issues/12837#issuecomment-830120718