package com.fadejimi.callrepresentatives.model

data class Representative(val name: String, val state: String, val region: String, val email: String,
                          val number: String) {
    val stateValue = "$state | $region"
}