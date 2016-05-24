
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

class graph extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Map[String, String],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(userInfo: Map[String, String]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {
import helper._

Seq[Any](format.raw/*1.33*/("""
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
        Password: """),_display_(/*16.20*/userInfo/*16.28*/.get("password")),format.raw/*16.44*/("""
    """),format.raw/*17.5*/("""</body>
</html>
"""))
      }
    }
  }

  def render(userInfo:Map[String, String]): play.twirl.api.HtmlFormat.Appendable = apply(userInfo)

  def f:((Map[String, String]) => play.twirl.api.HtmlFormat.Appendable) = (userInfo) => apply(userInfo)

  def ref: this.type = this

}


}

/**/
object graph extends graph_Scope0.graph
              /*
                  -- GENERATED --
                  DATE: Tue May 24 15:35:17 IST 2016
                  SOURCE: /home/jeremy/JavaPlay_BasicTests/app/views/graph.scala.html
                  HASH: 3d03b3975d66701ade8af404d44be436a0d51b25
                  MATRIX: 758->1|899->32|926->50|953->51|1123->195|1137->201|1199->242|1286->303|1300->309|1360->348|1412->373|1427->379|1489->420|1623->527|1640->535|1674->548|1725->572|1742->580|1779->596|1811->601
                  LINES: 27->1|32->1|33->3|34->4|38->8|38->8|38->8|39->9|39->9|39->9|40->10|40->10|40->10|45->15|45->15|45->15|46->16|46->16|46->16|47->17
                  -- GENERATED --
              */
          