/* 你是一段成熟的代码，要学会自己改bug */
'use strict'

const CryptoJS = require('crypto-js')

const param = {
/* 十六位十六进制数作为密钥 */
  key: CryptoJS.enc.Utf8.parse('1234123412ABCDEF'),
  /* 十六位十六进制数作为密钥偏移量 */
  iv: CryptoJS.enc.Utf8.parse('ABCDEF1234123412')
}

const defaultMethod = {
  /**
   * 解密方法
   * @param word
   * @returns {string}
   */
  decrypt (word) {
    let encryptedHexStr = CryptoJS.enc.Hex.parse(word)
    let srcs = CryptoJS.enc.Base64.stringify(encryptedHexStr)
    let decrypt = CryptoJS.AES.decrypt(srcs, param.key, { iv: param.iv, mode: CryptoJS.mode.CBC, padding: CryptoJS.pad.Pkcs7 })
    let decryptedStr = decrypt.toString(CryptoJS.enc.Utf8)
    return decryptedStr.toString()
  },
  /**
   * 加密方法
   * @param word
   * @returns {string}
   */
  encrypt (word) {
    let srcs = CryptoJS.enc.Utf8.parse(word)
    let encrypted = CryptoJS.AES.encrypt(srcs, param.key, { iv: param.iv, mode: CryptoJS.mode.CBC, padding: CryptoJS.pad.Pkcs7 })
    return encrypted.ciphertext.toString().toUpperCase()
  },
  /* 生成永远不重复的随机数 */
  getRandom (len, radix) {
    let chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('')
    let uuid = []
    radix = radix || chars.length
    if (len) {
      for (let i = 0; i < len; i++) {
        uuid[i] = chars[0 | Math.random() * radix]
      }
    } else {
      let r = null
      uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-'
      uuid[14] = '4'
      for (let i = 0; i < 36; i++) {
        if (!uuid[i]) {
          r = 0 | Math.random() * 16
          uuid[i] = chars[(i === 19) ? (r & 0x3) | 0x8 : r]
        }
      }
    }
    return uuid.join('')
  }
}

export default defaultMethod
