package examples.basicRouter.containers

import react._
import redux._
import redux.Redux._
import reactRedux._
import reactRedux.ReactRedux._

import org.scalajs.dom._
import scalajs.js
import scalajs.js._
import react.ComponentClass
import scala.scalajs.js.annotation.JSExport
import scala.scalajs.js.JSApp

import scala.scalajs.js.annotation.ScalaJSDefined
import react.ReactTypedConstructor
import scala.scalajs.js.annotation.JSExportAll
import reactRouter.ReactRouter._
import reactRouter._
import react.xml.XmlToCreatElement
 
 
import examples.basicRouter.store._
import examples.basicRouter.redux.components._
import reduxForm._
import reduxForm.ReduxForm._
import examples.basicRouter.redux.newActions._

@JSExport("App3")
object Index extends JSApp {

  override def main() {
    //  try {  
    val empty: HTMLAttributes = null
    val store = ConfigureStore.getInstanceStore

    //store.dispatch(Actions.initFriend()) 

    val mapStateToProps: js.Function = (state: js.Dictionary[Any]) => {

      val reduc2 = state.getOrElse("reduc2", null)
      js.Dynamic.literal("store" -> store, "friendList" -> reduc2)
    }
    val mapStateToProps2: js.Function = (state: js.Dynamic) => {
      js.Dynamic.literal("initialValues" -> state.accountData, "store" -> store)
    }

    // la fonction connect passe la valeur du store dans les props ainis que la fonction dispatch
    // attention à ce que l'objet connecté (friendlist commence par une minuscule et non une majuscule sinon la macro génère js.constructorOf du composant
     // pour les reduxForm, il ne faut pas d'objet connect
      val submitValidationForm = reduxForm(js.Dynamic.literal("form" -> "submitValidation"))(js.constructorOf[SubmitValidationForm])
    val syncValidationForm = reduxForm(js.Dynamic.literal("form" -> "syncValidation", "validate" -> ValidateSubmit.validate, "warn" -> ValidateSubmit.warn))(js.constructorOf[SyncValidationForm])
    val asyncValidateForm = reduxForm(js.Dynamic.literal("form" -> "AsyncValidation", "validate" -> ValidateAsyncSubmit.validate, "asyncValidate" -> ValidateAsyncSubmit.asyncValidate, "asyncBlurFields" -> js.Array("username")))(js.constructorOf[AsyncValidateForm])
    var initializeFromStateForm0 = reduxForm(js.Dynamic.literal("form" -> "initializeFromStateForm"))(js.constructorOf[InitializeFromStateForm])
    val initializeFromStateForm = connect(mapStateToProps2, null, null)(initializeFromStateForm0)
    val fieldNormalizingForm = reduxForm(js.Dynamic.literal("form" -> "fieldNormalizingForm", "initialValues" -> js.Dynamic.literal("min" -> 1, "max" -> 10)))(js.constructorOf[FieldNormalizingForm])
    val fieldArraysForm = reduxForm(js.Dynamic.literal("form" -> "FieldArrays", "validate" -> ValidateFieldArrays.validate))(js.constructorOf[FieldArraysForm])

    val selectingFormValuesForm1 = reduxForm(js.Dynamic.literal("form" -> "selectingFormValues"))(js.constructorOf[SelectingFormValuesForm])

    val selector = formValueSelector("selectingFormValues") // <-- same as form name
    val selectingFormValuesForm2 = connect(
      (state: js.Dictionary[Any]) => {
        // can select values individually
        val hasEmailValue = selector(state, "hasEmail")
        val favoriteColorValue = selector(state, "favoriteColor")
        // or together as a group
        val firstNameAndLastName = selector(state, "firstName", "lastName")
        val firstName = firstNameAndLastName.firstName
        val lastName = firstNameAndLastName.lastName
        js.Dynamic.literal(
          "hasEmailValue" -> hasEmailValue,
          "favoriteColorValue" -> favoriteColorValue,
          "fullName" -> (firstName + ":" + lastName))

      }, null, null)(selectingFormValuesForm1)

   
    @XmlToCreatElement(true)
    val provider = <Provider store={ store }>
                     <Router history={ browserHistory }>
                       <Route path="/submitValidationForm" component={ submitValidationForm }/>
                       <Route path="/syncValidationForm" component={ syncValidationForm }/>
                       <Route path="/asyncValidateForm" component={ asyncValidateForm }/>
                       <Route path="/initializeFromStateForm" component={ initializeFromStateForm }/>
                       <Route path="/fieldNormalizingForm" component={ fieldNormalizingForm }/>
                       <Route path="/wizardForm" component={ WizardForm }/>
                       <Route path="/fieldArraysForm" component={ fieldArraysForm }/>
                       <Route path="/selectingFormValuesForm" component={ selectingFormValuesForm2 }/>
                     </Router>
                   </Provider>
    ReactDOM.render(provider, document.getElementById("content"));

  }

} 
 
