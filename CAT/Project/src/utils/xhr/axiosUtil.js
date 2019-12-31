'use strict'
import paramType from './xhrParams'
import axios from 'axios'
import {
  Loading,
  Message
} from 'element-ui'
import qs from 'qs'

let instance = axios.create({
  baseUrl: '/CAT'
})
// 开启身份认证，携带cookie信息往服务器中
// instance.defaults.withCredentials = true

const defultMothed = {
  // 封装参数对象
  getAxiosObject ({
    url,
    params,
    mothed,
    responseType
  }) {
    const object = {
      url,
      method: mothed,
      responseType,
      header: paramType.HEADER
    }
    if (mothed === paramType.requestMothedType.POST) {
      // 如果是post请球将params转化成formData
      object['data'] = qs.stringify(params)
    } else {
      // 否则不需要
      object['params'] = params
    }
    return instance(object)
  },
  // 发送请求
  xhr ({
    that,
    url,
    params,
    success,
    mothed,
    responseType,
    other
  }) {
    // 跳用参数封装的列表
    const object = this.getAxiosObject({
      url,
      params,
      mothed,
      responseType
    })
    this.baseXhr({
      that,
      axiosList: [object],
      success,
      other
    })
  },
  // 最终发送请求的函数
  baseXhr ({
    that,
    axiosList = [],
    success = null,
    other
  }) {
    // 最终业务逻辑

    let mask = null
    if (other === true) {
      // 开启加载层
      mask = Loading.service({})
    }
    // 发送请求
    if (axiosList.length === 0) {
      console.log('系统异常')
      return
    }
    if (axiosList.length > 1) {
      // 合并请求
      axios.all(axiosList).then((...r) => {
        const res = r.map((r1) => {
          return r1.data
        })
        success(res)
      }).catch(() => {})
      if (mask !== null) {
        mask.close()
      }
    } else {
      // 发送单个请求
      axiosList[0].then((r) => {
        if (r.data.resultCode === undefined) {
          success(r)
          return
        }
        if (r.data.resultCode !== 0) {
          Message({
            type: 'error',
            message: r.data.message
          })
        } else {
          success(r.data)
        }
      }).catch(() => {})
      if (mask !== null) {
        mask.close()
      }
    }
  }
}
const exportMothed = {
  requestXhr ({
    that,
    url,
    params,
    success,
    mothed = paramType.requestMothedType.GET,
    responseType,
    other
  }) {
    defultMothed.xhr({
      that,
      url,
      params,
      success,
      mothed,
      responseType,
      other
    })
  },
  requestGet ({
    that,
    url,
    params,
    success,
    mothed = paramType.requestMothedType.GET,
    responseType,
    other
  }) {
    defultMothed.xhr({
      that,
      url,
      params,
      success,
      mothed,
      responseType,
      other
    })
  },
  requestPost ({
    that,
    url,
    params,
    success,
    mothed = paramType.requestMothedType.POST,
    responseType,
    other
  }) {
    defultMothed.xhr({
      that,
      url,
      params,
      success,
      mothed,
      responseType,
      other
    })
  },
  requestObject ({
    url = '',
    params = {},
    mothed = paramType.requestMothedType.GET,
    responseType = paramType.responseType.JSON
  }) {
    defultMothed.getAxiosObject({
      url,
      params,
      mothed,
      responseType
    })
  }
}
export default exportMothed
