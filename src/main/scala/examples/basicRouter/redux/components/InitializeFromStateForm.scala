package examples.basicRouter.redux.components

import react._
import org.scalajs.dom._
import scalajs.js
import scalajs.js.{ Any => JAny }
import scalajs.js.{ Dynamic => jDynamic }
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.JSConverters._
import reactRouter.ReactRouter._

import react.xml.XmlToCreatElement
//import reactBootstrap.ReactBootstrap._
import reactBootstrap._
import reactRouterBootstrap._
//import reactRouterBootstrap.ReactRouterBootstrap._
import scala.scalajs.js.UndefOr
import org.scalajs.dom.raw.HTMLInputElement
import org.scalajs.dom.raw.Event
import redux.Store
import redux.Redux._
import reduxForm._
import reduxForm.ReduxForm._
import scala.scalajs.js.JSConverters._
import examples.basicRouter.store._
import examples.basicRouter.redux.newActions._
import examples.basicRouter.redux.newActions.Actions._
import scala.scalajs.js.annotation.JSName
import examples.basicRouter.store._
 
import scala.concurrent.{ Await, Future }
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import examples.basicRouter.redux.newActions.ActionsAccount._

@ScalaJSDefined
class InitializeFromStateForm(var props: js.Dynamic, context: js.Dynamic) extends Component[js.Dynamic, js.Any](props, context) {
  val colors = js.Array("Red", "Orange", "Yellow", "Green", "Blue", "Indigo", "Violet")
  val actions = bindActionCreators(ActionsAccount, props.store.dispatch);
  val data = new AccountData("Jane", "Doe", 42, "female", true, "Blue", "Born to write amazing Redux code.")

  @XmlToCreatElement(true)
  override def render(): JAny = {
    <form onSubmit={ props.handleSubmit }>
      <div>
        <button type="button" onClick={ () => actions.loadAccount(data) }>Load Account</button>
      </div>
      <div>
        <label>First Name</label>
        <div>
          <Field name="firstName" component="input" type="text" placeholder="First Name"/>
        </div>
      </div>
      <div>
        <label>Last Name</label>
        <div>
          <Field name="lastName" component="input" type="text" placeholder="Last Name"/>
        </div>
      </div>
      <div>
        <label>Age</label>
        <div>
          <Field name="age" component="input" type="number" placeholder="Age"/>
        </div>
      </div>
      <div>
        <label>Sex</label>
        <div>
          <label><Field name="sex" component="input" type="radio" value="male"/> Male</label>
          <label><Field name="sex" component="input" type="radio" value="female"/> Female</label>
        </div>
      </div>
      <div>
        <label>Favorite Color</label>
        <div>
          <Field name="favoriteColor" component="select">
            <option value="">Select a color...</option>
            { colors.map { opt => <option value="{opt}">{ opt }</option> } }
          </Field>
        </div>
      </div>
      <div>
        <label htmlFor="employed">Employed</label>
        <div>
          <Field name="employed" id="employed" component="input" type="checkbox"/>
        </div>
      </div>
      <div>
        <label>Bio</label>
        <div>
          <Field name="bio" component="textarea"/>
        </div>
      </div>
      <div>
        <button type="submit" disabled={ props.pristine || props.submitting }>Submit</button>
        <button type="button" disabled={ props.pristine || props.submitting } onClick={ props.reset }>Undo Changes</button>
      </div>
    </form>

  }

}

 

 
 