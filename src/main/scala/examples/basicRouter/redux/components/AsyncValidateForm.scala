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
 @js.native
  class ValuesForm extends js.Object {
    var username:String = js.native
    var password:String = js.native

  }
@ScalaJSDefined
object ValidateAsyncSubmit extends js.Object {
  @ScalaJSDefined
  class Errors extends js.Object {
    var username = ""
    var password = ""

  }
 

  val fn1: js.Function0[Unit] = () => println("appel fonction apres timeout")
  val asyncValidate: js.Function = (values: js.Dynamic) => {
    println("on est dans la fonction asyncValidate")
     
    println("asyncValidate:content of value UserName=" + values.username.toString)
    throw new js.JavaScriptException(js.Dynamic.literal("username" -> "bad userName"))

  }

  //def validate( values:Values)  : Errors ={
   @ScalaJSDefined
  val validate: js.Function = (values:  js.Dynamic ) => {
    val errors = new Errors()
    try {
    if (js.isUndefined(values.username) ||  values.username == null || values.username.toString.trim == "") {
      errors.username = "Required"
    }
    if (js.isUndefined(values.password) || values.password == null || values.password.toString.trim == "") {
      errors.password = "Required"
    }
    } 
    catch {
      case ex:Exception => println("erreur validate" + ex.getMessage)
      
    }
    errors
  }

  
}

@ScalaJSDefined
class JSStateASyncValidationForm extends js.Object

@ScalaJSDefined
class AsyncValidateForm(var props: ReduxFormProps, context: js.Dynamic) extends Component[ReduxFormProps, JSStateASyncValidationForm](props, context) {
val submit : js.Function = ()  => println("on est dans le submit du formulaire")
  @XmlToCreatElement(true)
  val renderField: js.Function = (props: FieldProp) =>
    <div>
      <label>{ props.label }</label>
      <div>
        <input type={ props.`type` } onFocus={ (evt: js.Any) => props.input.onFocus } onDrop={ (evt: js.Any) => props.input.onDrop(evt) } onDragStart={ (evt: js.Any) => props.input.onDragStart(evt) } onBlur={ (evt: js.Any) => props.input.onBlur(evt) } onChange={ (evt: js.Any) => props.input.onChange(evt) } name={ props.name } value={ props.input.value } placeholder={ props.input.name }/>
        { if (props.meta.touched && !js.isUndefined(props.meta.error)) { <span>{ props.meta.error }</span> } }
        <span> { props.meta.error }  </span>
      </div>
    </div>

  @XmlToCreatElement(true)
  override def render(): JAny = {
    <form onSubmit={submit.bind(this) }>
      <Field name="username" type="text" component={ renderField } label="Username"/>
      <Field name="password" type="password" component={ renderField } label="Password"/>
      <div>
        <button type="submit" disabled={ props.submitting }>Submit</button>
        <button type="button" disabled={ props.pristine || props.submitting } onClick={ props.reset }>Clear Values</button>
      </div>
    </form>

  }

}

 