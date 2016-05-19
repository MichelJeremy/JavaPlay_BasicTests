
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object main_Scope0 {
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

class main extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Form[User],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(userForm: Form[User]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {
import helper._

Seq[Any](format.raw/*1.24*/("""
"""),format.raw/*3.1*/("""
"""),format.raw/*4.1*/("""<!DOCTYPE html>
<html lang="en">
    <head>
        <title>GET OUTOF HERE STALKER</title>
        <link rel="stylesheet" media="screen" href=""""),_display_(/*8.54*/routes/*8.60*/.Assets.versioned("stylesheets/main.css")),format.raw/*8.101*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(/*9.59*/routes/*9.65*/.Assets.versioned("images/favicon.png")),format.raw/*9.104*/("""">
        <script src=""""),_display_(/*10.23*/routes/*10.29*/.Assets.versioned("javascripts/hello.js")),format.raw/*10.70*/("""" type="text/javascript"></script>
    </head>
    
    <body>
        <h1>Fill the form, stalker</h1>
        """),_display_(/*15.10*/helper/*15.16*/.form(action = routes.Application.loginSubmit())/*15.64*/ {_display_(Seq[Any](format.raw/*15.66*/("""
            """),_display_(/*16.14*/helper/*16.20*/.inputText(userForm("email"))),format.raw/*16.49*/("""
            """),_display_(/*17.14*/helper/*17.20*/.inputPassword(userForm("password"))),format.raw/*17.56*/("""
            """),format.raw/*18.13*/("""<input type="submit" value="submit" name="submit" >
        """)))}),format.raw/*19.10*/("""
    """),format.raw/*20.5*/("""</body>
</html>
"""))
      }
    }
  }

  def render(userForm:Form[User]): play.twirl.api.HtmlFormat.Appendable = apply(userForm)

  def f:((Form[User]) => play.twirl.api.HtmlFormat.Appendable) = (userForm) => apply(userForm)

  def ref: this.type = this

}


}

/**/
object main extends main_Scope0.main
              /*
                  -- GENERATED --
                  DATE: Thu May 19 17:46:07 IST 2016
                  SOURCE: /home/jeremy/allyourbasearebelongtous/app/views/main.scala.html
                  HASH: 6b1eaa9160947b3b5426dd1264b3ca344e18fb12
                  MATRIX: 747->1|879->23|906->41|933->42|1102->185|1116->191|1178->232|1265->293|1279->299|1339->338|1391->363|1406->369|1468->410|1607->522|1622->528|1679->576|1719->578|1760->592|1775->598|1825->627|1866->641|1881->647|1938->683|1979->696|2071->757|2103->762
                  LINES: 27->1|32->1|33->3|34->4|38->8|38->8|38->8|39->9|39->9|39->9|40->10|40->10|40->10|45->15|45->15|45->15|45->15|46->16|46->16|46->16|47->17|47->17|47->17|48->18|49->19|50->20
                  -- GENERATED --
              */
          