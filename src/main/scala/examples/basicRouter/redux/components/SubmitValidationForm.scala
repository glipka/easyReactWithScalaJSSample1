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
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.concurrent.Promise

@ScalaJSDefined
class SubmitValidationForm(var props: ReduxFormProps, context: js.Dynamic) extends Component[ReduxFormProps, JSStateSubmitValidationForm](props, context) {

  // val sleep : js.Function = ms:Int => new js.Promise(resolve => window.setTimeout(resolve, ms))

  @ScalaJSDefined
  val submit: js.Function = (values: js.Dynamic) => {
    if (List(values.username.toString.trim).intersect(List("georges","dealan")).size == 0) {
    val erreur1 = new SubmissionError(js.Dynamic.literal("username" -> "username does not exist", "_error" -> "Login failed!"))
     val rejectedPromise1=js.Promise.reject(erreur1)
     rejectedPromise1
     
     } else if (values.password != "redux") {
        val erreur2 = new SubmissionError(js.Dynamic.literal("password" -> "wrong Password ", "_error" -> "Login failed!"))
     val rejectedPromise2=js.Promise.reject(erreur2)
     rejectedPromise2
    
      } else {
        val valeurs=js.JSON.stringify(values)
        println("contenu de valeurs avant le submit=" + valeurs)
        val mes= "You submitted$:  " + valeurs
         window.alert(mes)
      }

  }
  val endTimeOut: js.Function0[Any] = () => println("*** fin de timeout ***")
  
   

  override def componentDidMount(): Unit = {}
  
    
  @XmlToCreatElement(true)
  val renderField: js.Function = (props: FieldProp) =>
   
    <div>
      <label>{ props.label }</label>
      <div>
        <input type={ props.`type` } onFocus={ (evt: js.Any) => props.input.onFocus } onDrop={ (evt: js.Any) => props.input.onDrop(evt) } onDragStart={ (evt: js.Any) => props.input.onDragStart(evt) } onBlur={ (evt: js.Any) => props.input.onBlur(evt) } onChange={ (evt: js.Any) => props.input.onChange(evt) } name={ props.name } value={ props.input.value } placeholder={ props.input.name }/>
        { if (props.meta.touched && !js.isUndefined(props.meta.error)) { <span> { props.meta.error }  </span> } }
      </div>
    </div>
  // --------------------------------------------------------------------------------
  // 
  // --------------------------------------------------------------------------------

  @XmlToCreatElement(true)
  override def render(): JAny = {
    <form onSubmit={ props.handleSubmit(submit.bind(this)) }>
      <Field name="username" type="text" component={ renderField } label="Username"/>
      <Field name="password" type="password" component={ renderField } label="Password"/>
      { if (!js.isUndefined(props.error)) { <strong>{ props.error + " erreur detectee " }</strong> } }
      <div id="div1">
        <button type="submit" disabled={ props.submitting }>Log In</button>
        <button type="button" disabled={ props.pristine || props.submitting } onClick={ props.reset }>Clear Values</button>
      </div>
    </form>
  }

}

 