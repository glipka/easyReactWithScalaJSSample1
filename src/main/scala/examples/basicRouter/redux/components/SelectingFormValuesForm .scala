package examples.basicRouter.redux.components

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
import org.scalajs.dom.raw.Event
import redux.Store
import redux.Redux._
import reduxForm._
import reduxForm.ReduxForm._
import scala.scalajs.js.JSConverters._

@js.native
trait JSPropsSelectingFormValuesForm extends js.Object {
  // true if any of the Fields have been marked as touched, false otherwise.
  val pristine: Boolean = js.native
  val submitting: Boolean = js.native
  val hasEmailValue: js.Any = js.native
  val favoriteColorValue: js.Any = js.native
  val fullName: String = js.native
  val reset: js.Function = js.native
  val handleSubmit: js.Function = js.native
}

@ScalaJSDefined
class SelectingFormValuesForm(val props: JSPropsSelectingFormValuesForm, context: Any) extends Component[JSPropsSelectingFormValuesForm, js.Any](props, context) {
  val name1: String = ""

  override def componentWillMount: Unit = {}
  override def componentDidMount: Unit = {}
  override def componentWillReceiveProps(nextProps: JSPropsSelectingFormValuesForm, nextContext: js.Any) {}

  @XmlToCreatElement(true)
  override def render(): Any = {

    <form onSubmit={ props.handleSubmit }>
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
        <label htmlFor="hasEmail">Has Email?</label>
        <div>
          <Field name="hasEmail" id="hasEmail" component="input" type="checkbox"/>
        </div>
      </div>
      {
        if (!isUndefined(props.hasEmailValue)) {
          <div>
            <label>Email</label>
            <div>
              <Field name="email" component="input" type="email" placeholder="Email"/>
            </div>
          </div>
        }
      }
      <div>
        <label>Favorite Color</label>
        <div>
          <Field name="favoriteColor" component="select">
            <option></option>
            <option value="#ff0000">Red</option>
            <option value="#00ff00">Green</option>
            <option value="#0000ff">Blue</option>
          </Field>
        </div>
      </div>
      {
        if (!isUndefined(props.favoriteColorValue) && props.favoriteColorValue.toString != "") {
          <div style={
            js.Dynamic.literal(
              "height" -> 80,
              "width" -> 200,
              "margin" -> "10px auto",
              "backgroundColor" -> props.favoriteColorValue)
          }/>
        }
      }
      <div>
        <button type="submit" disabled={ props.pristine || props.submitting }>Submit { props.fullName }</button>
        <button type="button" disabled={ props.pristine || props.submitting } onClick={ props.reset }>
          Clear Values
        </button>
      </div>
    </form>

  }

} 

 