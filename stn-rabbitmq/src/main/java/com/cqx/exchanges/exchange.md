### direct 
通过消息的routing_key 跟exchange上的queue绑定起来，一个queue可以绑定多个routing_key

### fanout
一般用于广播
把queue绑定到exchange上，发布的消息经由exchange绑定到这些queue，这样就广播了
`it just broadcasts all the messages it receives to all the queues it knows. `

### topic
正则表达式匹配
kern.* 通配符来绑定所有 kern.开头的routing_key

### others
1. 一个信道channel 只能订阅一个队列queue