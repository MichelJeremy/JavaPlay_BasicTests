
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/jeremy/allyourbasearebelongtous/conf/routes
// @DATE:Thu May 19 17:46:45 IST 2016


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
