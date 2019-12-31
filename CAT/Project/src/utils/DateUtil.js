import verification from './VerificationUtil'
import baseParam from './BaseParam'

/**
 * 默认工具
 * @type {{}}
 */
const util = {
  /**
   * 取余（获取余数，整数）
   * @param number
   * @param divider
   * @returns {number[]}
   */
  getRemainder (number = 0, divider = 1) {
    // eslint-disable-next-line
    return [parseInt(number % divider), number /= divider]
  },
  /**
   * 数字转字符串
   * @param numberArray
   */
  toString (...numberArray) {
    if (numberArray.length === 0) {
      return ''
    }
    numberArray.forEach((number, index) => {
      numberArray[index] = number < 10 ? '0' + number : number + ''
    })
    return numberArray
  }
}

export default {
  /**
   * 格式化时长
   * @param value
   * @param format
   * @param getObject
   * @returns {string}
   */
  formatTime (value = 0, format = 'dd:mm:ss', getObject) {
    // eslint-disable-next-line
    let hour = 0, minute = 0, second = 0
    // eslint-disable-next-line
    let hourStr = '', minuteStr = '', secondStr = '';
    [second, value] = util.getRemainder(value, 60);
    [minute, value] = util.getRemainder(value, 60);
    [hour, value] = util.getRemainder(value, 60);
    [hourStr, minuteStr, secondStr] = util.toString(hour, minute, second)
    let content = format
    if (hour === 0) {
      content = content.replace(/d+:?/ig, '')
    }

    content = content.replace(/d+/ig, hourStr)
    content = content.replace(/m+/ig, minuteStr)
    content = content.replace(/s+/ig, secondStr)
    // 输出对象
    if (getObject || false) {
      return {
        time: content,
        hour,
        minute,
        second
      }
    }
    return content
  },
  /**
   * 格式化日期时间
   * @param value
   * @param format
   * @param language
   * @param getObject
   * @returns {*}
   */
  formatDate (value, format, language = 'Other', getObject) {
    let date = null
    if (verification.isDate(value)) {
      date = value
    } else {
      if (verification.isNull(value)) {
        date = new Date()
      } else {
        date = new Date(value)
      }
    }
    let Y = date.getFullYear()
    let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1)
    let D = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate())
    let h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours())
    let m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes())
    let s = (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds())
    let e = date.getDay()
    // 语言修改
    switch (language.toUpperCase()) {
      case 'CN':
        M = baseParam.DATE_TEXT.CN_MONTH[date.getMonth()]
        e = baseParam.DATE_TEXT.CN_WEEK[e]
        break
      case 'EN':
        M = baseParam.DATE_TEXT.EN_MONTH_ABBREVIATION[date.getMonth()]
        e = baseParam.DATE_TEXT.EN_WEEK_ABBREVIATION[e]
        break
      default:
        e = baseParam.DATE_TEXT.CN_WEEK[e]
        break
    }
    // 验证格式
    let content = format || 'yyyy-MM-dd HH:mm'
    content = content.replace(/y+/ig, Y)
    content = content.replace(/M+/g, M)
    content = content.replace(/d+/ig, D)
    content = content.replace(/H+/g, h)
    content = content.replace(/h+/g, h >= 12 ? h - 12 : h)
    content = content.replace(/m+/g, m)
    content = content.replace(/s+/ig, s)
    content = content.replace(/e+/ig, e)
    // 输出对象
    if (getObject || false) {
      return {
        date: content,
        timestamp: date.getTime(),
        year: date.getFullYear(),
        month: date.getMonth() + 1,
        day: date.getDate(),
        hour: date.getHours(),
        minute: date.getMinutes(),
        second: date.getSeconds(),
        week: date.getDay()
      }
    }
    return content
  }
}
