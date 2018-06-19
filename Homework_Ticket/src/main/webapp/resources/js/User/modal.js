/* ========================================================================
 * Bootstrap: modal.js v3.2.0
 * http://getbootstrap.com/javascript/#modals
 * ========================================================================
 * Copyright 2011-2014 Twitter, Inc.
 * Licensed under MIT (https://github.com/twbs/bootstrap/blob/master/LICENSE)
 * ======================================================================== */

//这里的 +function($){}(jQuery) 和 (function($){})(jQuery) 一个意思
+function($) {
    'use strict';

    // MODAL CLASS DEFINITION
    // ======================

    var Modal = function(element, options) {
        this.options = options
        this.$body = $(document.body)
        this.$element = $(element)
        this.$backdrop = this.isShown = null
        this.scrollbarWidth = 0

        // 如果是远程内容,就加载,并触发loaded.bs.modal事件
        if (this.options.remote) {
            this.$element.find('.modal-content').load(this.options.remote,
                $.proxy(function() {
                    this.$element.trigger('loaded.bs.modal')
                }, this))
        }
    }

    Modal.VERSION = '3.2.0'

    // 默认配置
    Modal.DEFAULTS = {
        backdrop : true, // 默认配置,要背景div(单击触发hide方法)
        keyboard : true, // 要desc键按了就消失的功能
        show : true // 模态框初始化之后就立即显示出来
    }

    // 如果是显示的状态,就隐藏,如果是未显示,则显示
    Modal.prototype.toggle = function(_relatedTarget) {
        return this.isShown ? this.hide() : this.show(_relatedTarget)
    }

    // 显示弹出框方法
    Modal.prototype.show = function(_relatedTarget) {
        var that = this
        var e = $.Event('show.bs.modal', {relatedTarget : _relatedTarget})

        this.$element.trigger(e)// 触发show.bs.modal事件

        // 如果已经显示了, 或者上面事件回调中调用了e.preventDefault 就 直接不处理,直接返回
        if (this.isShown || e.isDefaultPrevented()) return

        //改变显示状态为显示中
        this.isShown = true

        //有滚动条的话,就设置滚动条的宽度
        this.checkScrollbar()
        //overflow:hidden
        this.$body.addClass('modal-open')
        //padding-right 属性在原来的基础上 + 上面算出来的滚动条的宽度
        this.setScrollbar()

        //如果options.keyboard配置为true则监听keyup.dismiss.bs.modal事件, 功能就是按esc键,就调用hide方法
        this.escape()

        //为包含data-dismiss="modal"属性的元素注册关闭处理器(比如点x按钮,就隐藏模态框功能)
        this.$element.on('click.dismiss.bs.modal', '[data-dismiss="modal"]', $.proxy(this.hide, this))

        //backdrop函数:背景逻辑, 回调函数功能:显示model逻辑
        this.backdrop(function () {
            // 浏览器是否支持动画 & model的元素包含fade class
            var transition = $.support.transition && that.$element.hasClass('fade')

            // 没有父元素(例:还未append的$("<div></div>")),则将model附加到body上,
            if (!that.$element.parent().length) {
                that.$element.appendTo(that.$body) // don't move modals dom position
            }

            // 将model元素设置成显示(jq.show方法), 并移动到最上面
            that.$element.show().scrollTop(0)

            //动画效果准备
            if (transition) {
                that.$element[0].offsetWidth // force reflow
            }

            //设置不透明opacity:1, 设置显示(aria,给不方便人士用的)aria-hidden:false
            that.$element.addClass('in').attr('aria-hidden', false);

            //解绑并为document对象注册focusin.bs.modal事件, 具体处理是:如果不是model产生的,就触发model的facus事件
            //简单的说,就是聚焦,哈哈,看着我,看着我
            that.enforceFocus()

            //准备触发shown.bs.modal事件
            var e = $.Event('shown.bs.modal', { relatedTarget: _relatedTarget })

            //有动画,就动画完成后触发focus事件和shown.bs.modal事件
            transition ?
                that.$element.find('.modal-dialog') // wait for modal to slide in
                    .one('bsTransitionEnd', function () {
                        that.$element.trigger('focus').trigger(e)
                    })
                    .emulateTransitionEnd(300) :
                that.$element.trigger('focus').trigger(e)
        })
    }

    //隐藏model方法
    Modal.prototype.hide = function(e) {
        if (e) e.preventDefault() //不清楚作用场景

        //准备触发隐藏事件, 并触发
        e = $.Event('hide.bs.modal')
        this.$element.trigger(e)

        //如果model状态以及为未显示 或者上面事件回调函数中调用了preventDefault, 就不处理
        if (!this.isShown || e.isDefaultPrevented()) return

        //设置状态为不显示
        this.isShown = false

        //去掉显示时加上的class, 具体就是overflow:hidden
        this.$body.removeClass('modal-open')

        //还原padding-right属性 设置为'' (对应show中的setScrollbar)
        this.resetScrollbar()

        ////解除keyup.dismiss.bs.modal, 与show方法里的对应
        this.escape()

        //解除document对象的focusin.bs.modal事件绑定, (对应show中enforceFocus)
        $(document).off('focusin.bs.modal')

        //移除不透明设置(in), 设置为隐藏, 移除关闭处理器(比如点x按钮,就隐藏模态框功能)
        this.$element.removeClass('in').attr('aria-hidden', true).off('click.dismiss.bs.modal')

        //动画,然后调用hideModal方法(加上背景div的关联处理)
        $.support.transition && this.$element.hasClass('fade') ? this.$element
            .one('bsTransitionEnd', $.proxy(this.hideModal, this))
            .emulateTransitionEnd(300) : this.hideModal()
    }

    //解绑并为document对象注册focusin.bs.modal事件, 具体处理是:如果不是model产生的,就触发model的facus事件
    Modal.prototype.enforceFocus = function () {
        $(document).off('focusin.bs.modal') // guard against infinite focus loop
            .on('focusin.bs.modal', $.proxy(function (e) {
                if (this.$element[0] !== e.target && !this.$element.has(e.target).length) {
                    this.$element.trigger('focus')
                }
            }, this))
    }

    //实现按下esc键时,隐藏model功能,(根据配置)
    Modal.prototype.escape = function () {
        if (this.isShown && this.options.keyboard) {
            this.$element.on('keyup.dismiss.bs.modal', $.proxy(function (e) {
                e.which == 27 && this.hide()
            }, this))
        } else if (!this.isShown) {
            this.$element.off('keyup.dismiss.bs.modal')
        }
    }

    //隐藏关联方法,隐藏元素,并处理背景div,并触发隐藏完成事件
    Modal.prototype.hideModal = function () {
        var that = this
        //隐藏model自身(jq.hide)
        this.$element.hide()
        //隐藏背景逻辑
        this.backdrop(function () {
            //触发隐藏完成事件
            that.$element.trigger('hidden.bs.modal')
        })
    }

    //移除背景div
    Modal.prototype.removeBackdrop = function () {
        this.$backdrop && this.$backdrop.remove()
        this.$backdrop = null
    }

    //callback为具体的model的隐藏或显示逻辑,backdrop负责背景div逻辑
    Modal.prototype.backdrop = function (callback) {
        var that = this

        //是否包含fade样式(动画)
        var animate = this.$element.hasClass('fade') ? 'fade' : ''

        //如果为显示中 且 要背景div
        if (this.isShown && this.options.backdrop) {
            var doAnimate = $.support.transition && animate

            //添加背景div
            this.$backdrop = $('<div class="modal-backdrop ' + animate + '" />').appendTo(this.$body)

            //绑定click.dismiss.bs.modal事件,
            this.$element.on('click.dismiss.bs.modal', $.proxy(function (e) {
                //冒泡来的事件不管,不过一般model都在最外面,so,这里一般不会返回false;
                if (e.target !== e.currentTarget) return

                //如果backdrop配置参数为static, 则获取焦点,否则,调用隐藏方法
                this.options.backdrop == 'static' ? this.$element[0].focus.call(this.$element[0]) : this.hide.call(this)
            }, this))

            //准备动画
            if (doAnimate) this.$backdrop[0].offsetWidth // force reflow

            //设置背景div半透明opacity:0.5
            this.$backdrop.addClass('in')

            if (!callback) return

            //啊,背景div有动画就动画后回调,没有直接回调, 回调是指(显示或关闭逻辑)
            doAnimate ? this.$backdrop.one('bsTransitionEnd', callback).emulateTransitionEnd(150) : callback()

        } else if (!this.isShown && this.$backdrop) {//状态为未显示,且要背景div
            //去掉半透明效果
            this.$backdrop.removeClass('in')

            //回调函数: 移除遮罩div后回调
            var callbackRemove = function () {
                that.removeBackdrop()
                callback && callback()
            }

            //啊,能动画,就动画后回调上面的函数
            $.support.transition && this.$element.hasClass('fade') ?
                this.$backdrop.one('bsTransitionEnd', callbackRemove)
                    .emulateTransitionEnd(150) : callbackRemove()

        } else if (callback) {//显示但是不要背景div,且回调不为空,这里回调是显示model逻辑
            callback()
        }
    }

    //检查是否有滚动条,并计算滚动条宽度(猜的-.-!)
    Modal.prototype.checkScrollbar = function() {
        if (document.body.clientWidth >= window.innerWidth) return
        this.scrollbarWidth = this.scrollbarWidth || this.measureScrollbar()
    }

    //设置又内边距(估计和滚动条有关)
    Modal.prototype.setScrollbar = function() {
        var bodyPad = parseInt((this.$body.css('padding-right') || 0), 10)
        if (this.scrollbarWidth) this.$body.css('padding-right', bodyPad + this.scrollbarWidth)
    }

    //还原上面内边距设置
    Modal.prototype.resetScrollbar = function() {
        this.$body.css('padding-right', '')
    }

    //猜测是计算滚动条宽度的一种方法
    Modal.prototype.measureScrollbar = function() { // thx walsh
        var scrollDiv = document.createElement('div')
        scrollDiv.className = 'modal-scrollbar-measure'
        this.$body.append(scrollDiv)
        var scrollbarWidth = scrollDiv.offsetWidth - scrollDiv.clientWidth
        this.$body[0].removeChild(scrollDiv)
        return scrollbarWidth
    }


    // MODAL PLUGIN DEFINITION
    // =======================

    //对model的包装,支持批量操作,缓存,配置合并,方法调用,
    //option:model配置,   _relatedTarget:show.bs.modal和shown.bs.modal事件的关联relatedTarget
    function Plugin(option, _relatedTarget) {
        return this.each(function () {
            var $this   = $(this)
            //取缓存model对象
            var data    = $this.data('bs.modal')//缓存
            //合并配置参数
            var options = $.extend({}, Modal.DEFAULTS, $this.data(), typeof option == 'object' && option)

            //缓存没有,就new一个model
            if (!data) $this.data('bs.modal', (data = new Modal(this, options)))
            //如果参数为字符串(一般是'toggle','show','hien')
            if (typeof option == 'string') data[option](_relatedTarget)
            //如果配置为初始显示,就显示
            else if (options.show) data.show(_relatedTarget)
        })
    }

    //保存老的model属性,防冲突
    var old = $.fn.modal

    //导出
    $.fn.modal             = Plugin
    $.fn.modal.Constructor = Modal


    // MODAL NO CONFLICT
    // =================

    //有冲突时,还原$.fn.modal的引用,然后返回自己
    $.fn.modal.noConflict = function () {
        $.fn.modal = old
        return this
    }


    // MODAL DATA-API
    // ==============

    //为保护[data-toggle="modal"]属性的元素绑定单击事件:click.bs.modal.data-api
    //例如<a data-toggle="modal" data-target="#model1" href="http://localhost:8080/ysdai-mobile/">链接</a>\
    //对应上面的元素,插件就会为它绑定单击事件,使用$('#model1')来加载href,然后显示,我这里碰到,多次点击会产生多层背景div的问题,等有时间找找原因
    $(document).on('click.bs.modal.data-api', '[data-toggle="modal"]', function (e) {
        var $this   = $(this)
        var href    = $this.attr('href')
        //通过jq获取对应的model元素(根据当前元素data-target属性 或 href属性)
        var $target = $($this.attr('data-target') || (href && href.replace(/.*(?=#[^\s]+$)/, ''))) // strip for ie7
        //model元素有缓存 ? 直接调用toggle方法 : 没有就远程获取href内容显示
        var option  = $target.data('bs.modal') ? 'toggle' : $.extend({ remote: !/#/.test(href) && href }, $target.data(), $this.data())

        //阻止a标签浏览器默认行为
        if ($this.is('a')) e.preventDefault()

        //为model绑定show.bs.modal(显示前)事件
        $target.one('show.bs.modal', function (showEvent) {
            //如果前面注册的事件处理器一定调用了preventDefault方法,就不会显示,后面也就不绑定隐藏事件了,所以这里也不要处理了.
            if (showEvent.isDefaultPrevented()) return // only register focus restorer if modal will actually get shown

            //为model绑定hidden.bs.modal(隐藏后)事件, 如果当前元素可见,就触发当前元素焦点事件
            $target.one('hidden.bs.modal', function () {
                $this.is(':visible') && $this.trigger('focus')
            })
        })

        //调用Model.toggle方法,或者创建新的model对象,并从远处加载内容
        Plugin.call($target, option, this)
    })

}(jQuery);