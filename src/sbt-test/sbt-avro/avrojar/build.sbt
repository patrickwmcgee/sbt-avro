name := "avrojar-test"
scalaVersion := "2.11.11"

addArtifact(artifact in (AvroConfig, avroPackage), avroPackage in AvroConfig)

TaskKey[Unit]("checkJar") := {
  val jar = (avroPackage in AvroConfig).value
  IO.withTemporaryDirectory{ dir =>
    val files = IO.unzip(jar, dir)
    val expect = Set(dir / "a.avsc", dir / "META-INF" / "MANIFEST.MF")
    assert(files == expect, s"$files $expect")
  }
}
