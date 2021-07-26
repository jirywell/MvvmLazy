package com.rui.test.data.bean

import com.rui.mvvmlazy.binding.viewadapter.spinner.IKeyAndValue

/**

 */

class CityInfo : IKeyAndValue {
    var name: String? = null
    var code: String? = null

    constructor(name: String?, code: String?) {
        this.name = name
        this.code = code
    }

    override fun getKey(): String {
        return name ?: "";
    }

    override fun getValue(): String {
        return code ?: "";
    }
}