package com.teo.hospitalapp.util

import android.app.Activity
import android.content.Intent
import androidx.appcompat.widget.Toolbar
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import com.teo.hospitalapp.data.Hospital
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf


val testhos1 = Hospital(organisationID = 21232, organisationName = "Test1",fax = "1231231", website = "12312",
email = "test1", phone = "sdsaasd",parentName = "",parentODSCode = "",longitude = "",
latitude = "",postcode = "",county = "",city = "",address2 = "",address1 = "",isPimsManaged = false,organisationStatus = "",
sector = "",subType = "",organisationType = "",address3 = "",organisationCode = "")



/**
 * Returns the content description for the navigation button view in the toolbar.
 */
fun getToolbarNavigationContentDescription(activity: Activity, toolbarId: Int) =
    activity.findViewById<Toolbar>(toolbarId).navigationContentDescription as String

/**
 * Simplify testing Intents with Chooser
 *
 * @param matcher the actual intent before wrapped by Chooser Intent
 */
fun chooser(matcher: Matcher<Intent>): Matcher<Intent> = allOf(
    hasAction(Intent.ACTION_CHOOSER),
    hasExtra(`is`(Intent.EXTRA_INTENT), matcher)
)