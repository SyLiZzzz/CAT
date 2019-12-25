/**
 * 验证数据
 * @type {{isNull: (function(*=): boolean), isNotNull: (function(*=): boolean), isArray: (function(*): boolean), isNotArray: (function(*=): boolean), isNumber: (function(*): boolean), isNotNumber: (function(*=): boolean)}}
 */
export default {
  /**
     * 判断是否为空
     * @param p
     * @returns {boolean}
     */
  isNull (p) {
    return p === null || p === undefined || p === 'undefined' || p === ''
  },
  /**
     * 判断是否不为空
     * @param p
     * @returns {boolean}
     */
  isNotNull (p) {
    return !this.isNull(p)
  },
  /**
     * 判断是否为数组
     * @param array
     * @returns {boolean}
     */
  isArray (array) {
    return array != null && array instanceof Array
  },
  /**
     * 判断是否不是数组
     * @param array
     * @returns {boolean}
     */
  isNotArray (array) {
    return !this.isArray(array)
  },
  /**
     * 判断是否为数字
     * @param number
     * @returns {boolean}
     */
  isNumber (number) {
    return number != null && number instanceof Number
  },
  /**
     * 判断是否不为数字
     * @param number
     * @returns {boolean}
     */
  isNotNumber (number) {
    return !this.isNumber(number)
  },
  /**
     * 判断是否为时间类型
     * @param date
     * @returns {boolean}
     */
  isDate (date) {
    return date != null && date instanceof Date
  },
  /**
     * 判断是否不为时间类型
     * @param date
     * @returns {boolean}
     */
  isNotDate (date) {
    return !this.isDate(date)
  },
  /**
     * 判断是否为字符串
     * @param string
     * @returns {boolean}
     */
  isString (string) {
    return string != null && typeof string === 'string'
  },
  /**
     * 判断是否不为字符串
     * @param string
     * @returns {boolean}
     */
  isNotString (string) {
    return !this.isString(string)
  }
}

// export default {
//     verification,
// }
