new Vue({
    el: '#app',
    data: {
        message: 'Hello Vue.js!',
        todos: [
            {text: 'Learn JavaScript'},
            {text: 'Learn Vue.js'},
            {text: 'Build Something Awesome'}
        ]
    },
    methods: {
        reverseMessage: function () {
            this.message = this.message.split('').reverse().join('')
        }
    }
});

new Vue({
    el: '#app1',
    data: {
        newTodo: '',
        todos: [
            {text: 'Add some todos'}
        ]
    },
    methods: {
        addTodo: function () {
            var text = this.newTodo.trim()
            if (text) {
                this.todos.push({text: text})
                this.newTodo = ''
            }
        },
        removeTodo: function (index) {
            this.todos.splice(index, 1)
        }
    }
});

new Vue({
    el: '#app2',
    data: {
        top: true,
        items: [
            {msg: 'Foo'},
            {msg: 'Bar'}
        ]
    },
    methods: {
        toggleTop: function () {
            this.top = !this.top;
        }
    }
});

var SixTemp = Vue.extend({
    template: '<div>A Six Temp component!</div>'
});
var GlobalComp = Vue.extend({
    template: '<div>A local <six-temp></six-temp> component!</div>',
    components: {
        'six-temp': SixTemp
    }
});

new Vue({
    el: '#app3',
    data: {},
    methods: {}
});