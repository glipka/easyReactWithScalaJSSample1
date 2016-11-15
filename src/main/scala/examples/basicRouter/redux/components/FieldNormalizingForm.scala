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
class FieldNormalizingForm(var props: js.Dynamic, context: js.Dynamic) extends Component[js.Dynamic, js.Any](props, context) {

  def upper: js.Function = (value: String) => value.toUpperCase()
  def lower: js.Function = (value: String) => value.toLowerCase()

  //const lessThan = otherField =>
  //  (value, previousValue, allValues) => value < allValues[otherField] ? value : previousValue
  //const greaterThan = otherField =>
  //  (value, previousValue, allValues) => value > allValues[otherField] ? value : previousValue
  //
  //  
  //   val normalizePhone: js.Function = (value: String) => { normalizePhone(value,"") }

  val normalizePhone: js.Function = (value: String) => {
    
    val onlyNums = value //value.map(ch=> if (ch.isDigit) { ch} else {""}).mkString("")
    var valeurRetour = value
    val previousValue: String = ""
    if (value == null || value == "") {
      value
    } else if (value.length > previousValue.length) {
      // typing forward
      if (onlyNums.length == 3) {
        valeurRetour = onlyNums + "-"
      } else if (onlyNums.length == 6) {
        valeurRetour = onlyNums.substring(0, 3) + "-" + onlyNums.substring(3) + "-"
      }
    } else if (onlyNums.length <= 3) {
      valeurRetour = onlyNums
    } else if (onlyNums.length <= 6) {
      valeurRetour = onlyNums.substring(0, 3) + "-" + onlyNums.substring(3)
    } else
      valeurRetour = onlyNums.substring(0, 3) + "-" + onlyNums.substring(3, 6) + "-" + onlyNums.substring(6, 10)

    valeurRetour
  }

  @XmlToCreatElement(true)
  override def render(): JAny = {

    <form onSubmit={ props.handleSubmit }>
      <div>
        <label>Username</label>
        <div>
          <Field name="username" component="input" type="text" placeholder="Username" normalize={ lower }/>
        </div>
      </div>
      <div>
        <label>Shout</label>
        <div>
          <Field name="shout" component="input" type="text" placeholder="Shout something!" normalize={ upper }/>
        </div>
      </div>
      <div>
        <label>Phone</label>
        <div>
          <Field name="phone" component="input" type="text" placeholder="Phone Number" normalize={ normalizePhone }/>
        </div>
      </div>
      <div>
        <label>Min</label>
        <div>
          <Field name="min" component="input" type="number"/>
        </div>
      </div>
      <div>
        <label>Max</label>
        <div>
          <Field name="max" component="input" type="number"/>
        </div>
      </div>
      <div>
        <button type="submit" disabled={ props.submitting }>Submit</button>
        <button type="button" disabled={ props.pristine || props.submitting } onClick={ props.reset }>
          Clear Values
        </button>
      </div>
    </form>

  }

}

 

 
 