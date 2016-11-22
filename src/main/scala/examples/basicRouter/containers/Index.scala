/*
 # Copyright 2016 Georges Lipka
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
*/
package examples.basicRouter.containers

import com.glipka.easyReactJS.react._
import com.glipka.easyReactJS.redux._
import com.glipka.easyReactJS.redux.Redux._
import com.glipka.easyReactJS.reactRedux._
import com.glipka.easyReactJS.reactRedux.ReactRedux._

import org.scalajs.dom._
import scalajs.js
import scalajs.js._
import com.glipka.easyReactJS.react.ComponentClass
import scala.scalajs.js.annotation.JSExport
import scala.scalajs.js.JSApp

import scala.scalajs.js.annotation.ScalaJSDefined
import com.glipka.easyReactJS.react.ReactTypedConstructor
import scala.scalajs.js.annotation.JSExportAll
import com.glipka.easyReactJS.reactRouter.ReactRouter._
import com.glipka.easyReactJS.reactRouter._
import com.glipka.easyReactJS.react.xml.XmlToCreatElement

import examples.basicRouter.store._
import examples.basicRouter.redux.components._
import com.glipka.easyReactJS.reduxForm._
import com.glipka.easyReactJS.reduxForm.ReduxForm._
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
    val home: js.Function = () => {
      <div>
        <h2>Page Principale</h2>
      </div>
    }
    val red = js.Dynamic.literal("color" -> "red")
    @XmlToCreatElement(true)
    val provider2 = <Provider store={ store }>
                      <BrowserRouter>
                        <div>
                          <div>
                            <h1>*** samples Redux Form ***</h1>
                            <ul role="nav">
                               <li><Link to="/submitValidationForm" activeStyle={ red }>Redux-Form : submitValidationForm</Link></li>
                              <li><Link to="/syncValidationForm" activeStyle={ red }>Redux-Form : syncValidationForm</Link></li>
                              <li><Link to="/asyncValidateForm" activeStyle={ red }>Redux-Form : AsyncValidateForm</Link></li>
                              <li><Link to="/initializeFromStateForm" activeStyle={ red }>Redux-Form : initializeFromStateForm</Link></li>
                              <li><Link to="/selectingFormValuesForm" activeStyle={ red }>Redux-Form : selectingFormValuesForm</Link></li>
                              <li><Link to="/fieldNormalizingForm" activeStyle={ red }>Redux-Form : fieldNormalizingForm</Link></li>
                              <li><Link to="/fieldArraysForm" activeStyle={ red }>Redux-Form : fieldArrayForm</Link></li>
                              <li><Link to="/wizardForm" activeStyle={ red }>Redux-Form : wizardForm</Link></li>
                              <li><Link to="/griddle" activeStyle={ red }>Griddle</Link></li>
                            </ul>
                            <hr/>
                          </div>
                          <div>
                            <Match exactly={ true } pattern="/" component={ home }/>
                            <Match pattern="/submitValidationForm" component={ submitValidationForm }/>
                            <Match pattern="/syncValidationForm" component={ syncValidationForm }/>
                            <Match pattern="/asyncValidateForm" component={ asyncValidateForm }/>
                            <Match pattern="/initializeFromStateForm" component={ initializeFromStateForm }/>
                            <Match pattern="/fieldNormalizingForm" component={ fieldNormalizingForm }/>
                            <Match pattern="/wizardForm" component={ WizardForm }/>
                            <Match pattern="/fieldArraysForm" component={ fieldArraysForm }/>
                            <Match pattern="/selectingFormValuesForm" component={ selectingFormValuesForm2 }/>
                            <Match pattern="/griddle" component={ Griddle1 }/>
                          </div>
                        </div>
                      </BrowserRouter>
                    </Provider>

    ReactDOM.render(provider2, document.getElementById("content"));

  }

} 

