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

@ScalaJSDefined
class JSStateSubmitValidationForm extends js.Object

@ScalaJSDefined
object ValidateSubmit extends js.Object {

  @ScalaJSDefined
  class Errors extends js.Object {
    var username = ""
    var email = ""
    var age = ""
  }

  @ScalaJSDefined
  class Values extends js.Object {
    var username = ""
    var email = ""
    var age = ""
  }

  //def validate( values:Values)  : Errors ={
  val validate: js.Function = (values: js.Dynamic) => {
    val errors = new Errors()

    if (js.isUndefined(values.username ) || values.username == "") {
      errors.username = "Required"
    } else if (values.username.toString.length > 15) {
      errors.username = "Must be 15 characters or less"
    }

    if (js.isUndefined(values.email )|| values.email == "") {
      errors.email = "Required"
    } else if (!values.email.toString.contains("@")) {
      errors.email = "Invalid email address"
    }

    if (js.isUndefined(values.age) || values.age == "") {
      errors.age = "Required"

    } else if (!values.age.toString.forall(chr => chr.isDigit)) {
      errors.age = "Must be a number"
    } else if (values.age.toString.toInt < 18) {
      errors.age = "Sorry, you must be at least 18 years old"
    }
    errors
  }

  val warn: js.Function = (values: js.Dynamic) => {
    val errors = new Errors()
    if (!js.isUndefined(values.age) && values.age.toString.toInt < 19) {
      errors.age = "Hmm, you seem a bit young..."
    }
    errors
  }

}

 

@ScalaJSDefined
class SyncValidationForm(var props: ReduxFormProps, context: js.Dynamic) extends Component[ReduxFormProps, Any](props, context) {

  @XmlToCreatElement(true)
  val renderField: js.Function = (props: FieldProp) =>
    <div>
      <label>{ props.label }</label>
      <div>
        <input type={ props.`type` } onFocus={ (evt: js.Any) => props.input.onFocus } onDrop={ (evt: js.Any) => props.input.onDrop(evt) } onDragStart={ (evt: js.Any) => props.input.onDragStart(evt) } onBlur={ (evt: js.Any) => props.input.onBlur(evt) } onChange={ (evt: js.Any) => props.input.onChange(evt) } name={ props.name } value={ props.input.value } placeholder={ props.input.name }/>
        <span> { props.meta.error }  </span>
      </div>
    </div>
  
  @XmlToCreatElement(true)
  override def render(): JAny = {
    <form onSubmit={ props.handleSubmit }>
      <Field name="username" type="text" component={renderField } label="Username"/>
      <Field name="email" type="email" component={ renderField } label="Email"/>
      <Field name="age" type="text" component={ renderField } label="Age"/>
      <div>
        <button type="submit" disabled={ props.submitting }>Submit</button>
        <button type="button" disabled={ props.pristine || props.submitting } onClick={ props.reset }>Clear Values</button>
      </div>
    </form>

  }

}

 
 