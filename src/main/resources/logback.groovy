def daemon = 'api' // service name
/**
 * Set default logging level
 * http://logback.qos.ch/apidocs/ch/qos/logback/classic/Level.html#toLevel%28string%29
 */
def level = toLevel(System.getenv('LOGGING_LEVEL'), WARN)
def sysloggerHost = 'edibox'

def patterns = [
        file:   '%date{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %level %logger:%line - %message%n',
        syslog: daemon + ': [%thread] %level %logger:%line - %message%rootException',
        visual: '%date{yyyy-MM-dd HH:mm:ss.SSS} %gray([%thread]) %highlight(%level) %cyan(%logger{32}) : %green(%line) - %message%n'
]

appender('SYSLOG', SyslogAppender) {
    syslogHost = sysloggerHost //'thoth.redlounge.io'
    facility = 'daemon'
    suffixPattern = patterns.syslog
}

appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = patterns.visual
    }
}

/* we do not use file logging by default, see SYSLOG appender above
appender('FILE', FileAppender) {
    file = dir + File.separator + daemon + '.log'
    encoder(PatternLayoutEncoder) {
        pattern = patterns.file
    }
}
*/

logger('net.rainmore', null)

// Third-party libraries
logger('org.hibernate', WARN)
logger('org.springframework', WARN)
logger('org.springframework.boot', INFO)
logger('org.springframework.security', WARN)
logger('org.thymeleaf', WARN)
logger('com.querydsl', WARN)

root(level, ['STDOUT', 'SYSLOG'])
