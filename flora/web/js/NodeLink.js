function NodeLink(id, parentId, text, parentNode){
	this.id = id;
	this.parentId = parentId;
	this.text = text;
	this.parentNode = parentNode;
}

NodeLink.prototype = {
	constructor : NodeLink,
	addNode : function(node){
		_this = this;
		_this.next = node;
		node.parentNode = _this;
	},
	removeNextNode : function(){
		_this = this;
		_this.next = null;
	},
	removeSelf : function(){
		_this = this;
		_this.parentNode.next = _this.next;
		if(_this.next != null)
			_this.next.parentNode = _this.parentNode;
	},
	isEmpty : function(){
		if(this.next == null)
			return true;
		return false;
	}
}