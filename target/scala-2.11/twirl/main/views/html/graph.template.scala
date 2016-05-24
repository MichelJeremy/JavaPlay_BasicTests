
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object graph_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._

class graph extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[Map[String, String],List[String],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(userInfo: Map[String, String], list: List[String]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {
import helper._

Seq[Any](format.raw/*1.53*/("""
"""),format.raw/*3.1*/("""
"""),format.raw/*4.1*/("""<!DOCTYPE html>
<html lang="en">
    <head>
        <title>GET OUT OF HERE STALKER</title>
        <link rel="stylesheet" media="screen" href=""""),_display_(/*8.54*/routes/*8.60*/.Assets.versioned("stylesheets/main.css")),format.raw/*8.101*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(/*9.59*/routes/*9.65*/.Assets.versioned("images/favicon.png")),format.raw/*9.104*/("""">
        <script src=""""),_display_(/*10.23*/routes/*10.29*/.Assets.versioned("javascripts/hello.js")),format.raw/*10.70*/("""" type="text/javascript"></script>
    </head>
    
    <body>
        <h1>User infos</h1>
        Email: """),_display_(/*15.17*/userInfo/*15.25*/.get("email")),format.raw/*15.38*/("""<br>
        Password: """),_display_(/*16.20*/userInfo/*16.28*/.get("password")),format.raw/*16.44*/("""<br>
        """),_display_(/*17.10*/for(l <- 0 until list.size) yield /*17.37*/ {_display_(Seq[Any](format.raw/*17.39*/("""
            """),_display_(/*18.14*/list/*18.18*/.get(l)),format.raw/*18.25*/("""<br>         
        """)))}),format.raw/*19.10*/("""
    """),format.raw/*20.5*/("""</body>
</html>
"""))
      }
    }
  }

  def render(userInfo:Map[String, String],list:List[String]): play.twirl.api.HtmlFormat.Appendable = apply(userInfo,list)

  def f:((Map[String, String],List[String]) => play.twirl.api.HtmlFormat.Appendable) = (userInfo,list) => apply(userInfo,list)

  def ref: this.type = this

}


}

/**/
object graph extends graph_Scope0.graph
              /*
                  -- GENERATED --
                  DATE: Tue May 24 17:28:41 IST 2016
                  SOURCE: /home/jeremy/JavaPlay_BasicTests/app/views/graph.scala.html
                  HASH: aedc7f0916c546d5fbc9add320699d1640891fdb
                  MATRIX: 771->1|932->52|959->70|986->71|1156->215|1170->221|1232->262|1319->323|1333->329|1393->368|1445->393|1460->399|1522->440|1656->547|1673->555|1707->568|1758->592|1775->600|1812->616|1853->630|1896->657|1936->659|1977->673|1990->677|2018->684|2072->707|2104->712
                  LINES: 27->1|32->1|33->3|34->4|38->8|38->8|38->8|39->9|39->9|39->9|40->10|40->10|40->10|45->15|45->15|45->15|46->16|46->16|46->16|47->17|47->17|47->17|48->18|48->18|48->18|49->19|50->20
                  -- GENERATED --
              */
          