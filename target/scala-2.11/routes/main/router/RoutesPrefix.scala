
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/jeremy/JavaPlay_BasicTests/conf/routes
// @DATE:Tue May 24 15:17:36 IST 2016


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
