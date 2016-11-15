package examples.basicRouter.containers
import react._
import org.scalajs.dom._
import scalajs.js
import scalajs.js._
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.JSConverters._
import reactRouter.ReactRouter._

import react.xml.XmlToCreatElement
import reactBootstrap.ReactBootstrap._
import reactBootstrap._
import reactRouterBootstrap._
import reactRouterBootstrap.ReactRouterBootstrap._
import scala.scalajs.js.UndefOr
import org.scalajs.dom.raw.HTMLInputElement

import scala.scalajs.js.JSConverters._

@js.native
trait JSPropsApp[P] extends js.Object {
  def key: UndefOr[String] = js.native
  def ref: UndefOr[js.Function] = js.native
  def children: PropsChildren = js.native

}

@ScalaJSDefined
class JSStateApp extends js.Object {
  var selectedKey = ""

}

@ScalaJSDefined
class App1(val props: JSPropsApp[Any]) extends Component[JSPropsApp[Any], JSStateApp](props) {

  def onSelect: js.Function = (eventKey: Event) => {
    println(s"Alert from menu item.\neventKey: $eventKey  }");
    var newState = this.state
    newState.selectedKey = eventKey.target.valueOf().asInstanceOf[HTMLInputElement].value
    setState(newState)
  }
  val red = js.Dynamic.literal("color" -> "red")
  override def componentWillMount: Unit = { this.state = new JSStateApp() {} }
  override def componentWillReceiveProps(nextProps: JSPropsApp[Any], nextContext: js.Any) {}

  @XmlToCreatElement(true)
  override def render(): Any = {
    <div>
      <h1>*** Liste des Applications ***</h1>
      <ul role="nav">
        <li><IndexLink to="/" activeStyle={ red }>Home</IndexLink></li>
        <li><Link to="/boostrapComponents" activeStyle={ red }>BootstrapComponents</Link></li>
        <li><Link to="/json" activeStyle={ red }>Json1 Sample</Link></li>
        <li><Link to="/sampleForm" activeStyle={ red }>Redux-Form : sampleForm</Link></li>
        <li><Link to="/submitValidationForm" activeStyle={ red }>Redux-Form : submitValidationForm</Link></li>
        <li><Link to="/syncValidationForm" activeStyle={ red }>Redux-Form : syncValidationForm</Link></li>
        <li><Link to="/asyncValidateForm" activeStyle={ red }>Redux-Form : AsyncValidateForm</Link></li>
        <li><Link to="/initializeFromStateForm" activeStyle={ red }>Redux-Form : initializeFromStateForm</Link></li>
        <li><Link to="/selectingFormValuesForm" activeStyle={ red }>Redux-Form : selectingFormValuesForm</Link></li>
        <li><Link to="/fieldNormalizingForm" activeStyle={ red }>Redux-Form : fieldNormalizingForm</Link></li>
        <li><Link to="/fieldArraysForm" activeStyle={ red }>Redux-Form : fieldArrayForm</Link></li>
        <li><Link to="/wizardForm" activeStyle={ red }>Redux-Form : wizardForm</Link></li>
        <li><Link to="/friends" activeStyle={ red }>Application Friends</Link></li>
        <li><Link to="/repos" activeStyle={ red }>Repos</Link></li>
      </ul>
      { this.props.children }
    </div>

  }

} 

/*
 
    * 
    *  <Navbar>
      <Nav activeKey={ this.state.selectedKey } onSelect={ onSelect.bind(this) }>
        <IndexLink  to="/">
          <NavItem eventKey="1" activeStyle={ red }>Home</NavItem>
        </IndexLink >
        <Link  to="/boostrapComponents" activeStyle={ red }>
          <NavItem eventKey="2">BootstrapComponents</NavItem>
        </Link >
      </Nav>
    </Navbar>
    */
 