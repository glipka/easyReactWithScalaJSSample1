package examples.basicRouter.redux.components

import react._
import org.scalajs.dom._
import scalajs.js
import scalajs.js.{ Any => JAny }
import scalajs.js.{ Dynamic => jDynamic }
import scalajs.js.Dynamic._
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
object ValidateWizardForm extends js.Object {

  @ScalaJSDefined
  class Errors extends js.Object {
    var firstname = ""
    var lastname = ""
    var email = ""
    var sex = ""
    var favoriteColor = ""
  }

  @ScalaJSDefined
  class Values extends js.Object {
    var firstname = ""
    var lastname = ""
    var email = ""
    var sex = ""
    var favoriteColor = ""
  }

  //def validate( values:Values)  : Errors ={
  val validate: js.Function = (values: js.Dynamic) => {
    val errors = new Errors()

    if (js.isUndefined(values.firstname)  || values.firstname == "") {
      errors.firstname = "Required"
    }
    if (js.isUndefined(values.lastname) || values.lastname == "") {
      errors.lastname = "Required"
    }

    if (js.isUndefined(values.email) || values.email == "") {
      errors.email = "Required"
    } else if (!values.email.toString.contains("@")) {
      errors.email = "Invalid email address"
    }

    if (js.isUndefined(values.sex ) || values.sex == "") {
      errors.sex = "Required"

    }
    if (js.isUndefined(values.favoriteColor) || values.favoriteColor == "") {
      errors.favoriteColor = "Required"
    }
    errors
  }

}

@ScalaJSDefined
class JSStateWizardForm(var page: Int) extends js.Object

@ScalaJSDefined
class WizardForm(var props: ReduxFormProps, context: JSStateWizardForm) extends Component[ReduxFormProps, JSStateWizardForm](props, context) {
  val colors = js.Array("Red", "Orange", "Yellow", "Green", "Blue", "Indigo", "Violet")
  @XmlToCreatElement(true)
  val renderField: js.Function = (props: js.Dynamic) =>
    <div>
      <label>{ props.label }</label>
      <div>
        <input type={ props.`type` } onFocus={ (evt: js.Any) => props.input.onFocus } onDrop={ (evt: js.Any) => props.input.onDrop(evt) } onDragStart={ (evt: js.Any) => props.input.onDragStart(evt) } onBLur={ (evt: js.Any) => props.input.onBlur(evt) } onChange={ (evt: js.Any) => props.input.onChange(evt) } name={ props.name } value={ props.input.value } placeholder={ props.input.name }/>
        <span> { props.meta.error }  </span>
      </div>
    </div>

  @XmlToCreatElement(true)
  val wizardFormFirstPage: js.Function = (props: js.Dynamic) =>
    <form onSubmit={ props.handleSubmit }>
      <Field name="firstName" type="text" component={ renderField } label="First Name"/>
      <Field name="lastName" type="text" component={ renderField } label="Last Name"/>
      <div>
        <button type="submit" className="next">Next</button>
      </div>
    </form>

  @XmlToCreatElement(true)
  val wizardFormSecondPage: js.Function = (props: js.Dynamic) =>
    <form onSubmit={ props.handleSubmit }>
      <Field name="email" type="email" component={ renderField } label="Email"/>
      <div>
        <label>Sex</label>
        <div>
          <label><Field name="sex" component="input" type="radio" value="male"/> Male</label>
          <label><Field name="sex" component="input" type="radio" value="female"/> Female</label>
          <Field name="sex" component={ renderField }/>
        </div>
      </div>
      <div>
        <button type="button" className="previous" onClick={ props.previousPage }>PreviousPage</button>
        <button type="submit" className="next">Next</button>
      </div>
    </form>

  @XmlToCreatElement(true)
  val favoriteColor: js.Function = (props: js.Dynamic) => {
    <div>
      <select onFocus={ (evt: js.Any) => props.input.onFocus } onDrop={ (evt: js.Any) => props.input.onDrop(evt) } onDragStart={ (evt: js.Any) => props.input.onDragStart(evt) } onBLur={ (evt: js.Any) => props.input.onBlur(evt) } onChange={ (evt: js.Any) => props.input.onChange(evt) }>
        <option value="">Select a color...</option>
        { colors.map(valeur => <option value="{valeur}">{ valeur }</option>) }
      </select>
      { <span>{ props.meta.error }</span> }
    </div>

  }
  @XmlToCreatElement(true)
  val wizardFormThirdPage: js.Function = (props: js.Dynamic) =>
    <form onSubmit={ props.handleSubmit }>
      <div>
        <label>Favorite Color</label>
        <Field name="favoriteColor" component={ favoriteColor }/>
      </div>
      <div>
        <label htmlFor="employed">Employed</label>
        <div>
          <Field name="employed" id="employed" component="input" type="checkbox"/>
        </div>
      </div>
      <div>
        <label>Notes</label>
        <div>
          <Field name="notes" component="textarea" placeholder="Notes"/>
        </div>
      </div>
      <div>
        <button type="button" className="previous" onClick={ props.previousPage }>Previous</button>
        <button type="submit" disabled={ props.pristine || props.submitting }>Submit</button>
      </div>
    </form>

  //  val WizardFormFirstPage1 = reduxForm(js.Dynamic.literal("form" -> "WizardFormFirstPage", "destroyOnUnmount" -> false, "validate" -> ValidateWizardForm.validate))(js.constructorOf[WizardFormFirstPage])
  val WizardFormFirstPage1 = reduxForm(js.Dynamic.literal("form" -> "WizardFormFirstPage", "destroyOnUnmount" -> false, "validate" -> ValidateWizardForm.validate))(wizardFormFirstPage)
  val WizardFormSecondPage1 = reduxForm(js.Dynamic.literal("form" -> "WizardFormSecondPage", "destroyOnUnmount" -> false, "validate" -> ValidateWizardForm.validate))(wizardFormSecondPage)
  val WizardFormThirdPage1 = reduxForm(js.Dynamic.literal("form" -> "WizardFormThirdPage", "destroyOnUnmount" -> false, "validate" -> ValidateWizardForm.validate))(wizardFormThirdPage)

  override def componentWillMount: Unit = {
    this.setState(new JSStateWizardForm(1))
  }

  val nextPage: js.Function = () => {
    val currentState = this.state
    if (currentState.page > 3) {
      this.setState(new JSStateWizardForm(1))
    } else {
      this.setState(new JSStateWizardForm(currentState.page + 1))
    }
  }

  val previousPage: js.Function = () => {
    val currentState = this.state
    this.setState(new JSStateWizardForm(currentState.page - 1))
  }
val onSubmit : js.Function = () => println("you have submitted ... ")

  @XmlToCreatElement(true)
  override def render(): JAny = {
    <div>
      { val pageNumber = state.page }
      { if (state.page == 1) <WizardFormFirstPage1 onSubmit={ nextPage.bind(this) }/> }
      { if (state.page == 2) <WizardFormSecondPage1 previousPage={ previousPage.bind(this) } onSubmit={ nextPage.bind(this) }/> }
      { if (state.page == 3) <WizardFormThirdPage1 previousPage={ previousPage.bind(this) } onSubmit={ onSubmit.bind(this) }/> }
    </div>
  }
}

 

 

  