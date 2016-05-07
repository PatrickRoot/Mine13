var Com1 = React.createClass({
    render:function(){
        return (<h1>Hello {this.props.name}</h1>);
    }
});

React.render(<Com1 name="ABC" />, document.getElementById("container"));