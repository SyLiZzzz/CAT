'use strict'
import $http from './xhr/axiosUtil'
export default {
  // get请求的封装
  xhrGet (that, url, params, success, responseType, other = true) {
    // 对他进行封装
    $http.requestGet({
      that,
      url,
      params,
      success,
      responseType,
      other
    })
  },
  // post请求的封装
  xhrPost (that, url, params, success, responseType, other = true) {
    $http.requestPost({
      that,
      url,
      params,
      success,
      responseType,
      other
    })
  },
  // ajax请求封装
  xhr (that, url, params, success, responseType, other = true) {
    $http.requestXhr({
      that,
      url,
      params,
      success,
      responseType,
      other
    })
  }
  // 合并请求的封装
  //   xhrAll () {
  //
  //   }
}
