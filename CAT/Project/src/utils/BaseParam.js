/**
 * 初始参数
 * @type {{VERSION: string, BASE_HTTP: string, DATE_TEXT: {CN_MONTH: string[], EN_MONTH: string[], EN_MONTH_ABBREVIATION: string[], CN_WEEK: string[], EN_WEEK: string[], EN_WEEK_ABBREVIATION: string[]}, HEADER: {}, SERIAL_NUM: {"1": string[], "2": string[], "3": string[], "4": string[], "5": string[]}}}
 */
export default {
  /**
   * 版本信息
   */
  VERSION: '1.0',
  /**
   * webSocket地址
   */
  WEB_SOCKET_ADDRESS: 'ws://127.0.0.1:8888/room/',
  /**
   * 接口地址
   */
  ACTION_ADDRESS: {

  },
  /**
   * 日期文字描述
   */
  DATE_TEXT: {
    CN_MONTH: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
    EN_MONTH: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
    EN_MONTH_ABBREVIATION: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
    CN_WEEK: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
    CN_WEEK_ABBREVIATION: ['日', '一', '二', '三', '四', '五', '六'],
    EN_WEEK: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
    EN_WEEK_ABBREVIATION: ['Sun.', 'Mon.', 'Tues.', 'Wed.', 'Thur.', 'Fri.', 'Sat.']
  },
  /**
   * 类型序号
   */
  SERIAL_NUM: {
    1: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'],
    2: ['I', 'II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII', 'IX', 'X', 'XI', 'XII', 'XIII'],
    3: ['①', '②', '③', '④', '⑤', '⑥', '⑦', '⑧', '⑨', '⑩'],
    4: ['一', '二', '三', '四', '五', '六', '七', '八', '九', '十'],
    5: ['壹', '貳', '叁', '肆', '伍', '陆', '柒', '捌', '玖', '拾']
  }
}
