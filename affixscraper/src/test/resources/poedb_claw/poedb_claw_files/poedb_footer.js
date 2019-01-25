$(document).ready(function() {
  $("table").tablesorter();
  // Create the tooltips only when document ready
  qtip_hover();
  // scrollToTop
  $("#toTop").scrollToTop();
});

function getLang(href) {
    var el = document.createElement('a');
    el.href = href;
    var pathname = el.pathname.substring(0, 4);
    if ($.inArray(pathname, ['/us/', '/cn/', '/ru/', '/pt/', '/th/', '/fr/', '/de/', '/sp/']) > -1) {
        return pathname.substring(1, 3);
    }
}

function qtip_hover() {
  // MAKE SURE YOUR SELECTOR MATCHES SOMETHING IN YOUR HTML!!!
  $('.page a').each(function() {
    if (getFilenameValid($(this).attr('href'))) {
      var n = getURLParameter($(this).attr('href'), 'n');
      if (!n) {
        return; // equal continue
      }
      var fn = getURLFilename($(this).attr('href'));
      fn = fn.substr(0, fn.lastIndexOf('.'));
      var url = 'hover.php?n=' + n + '&t=' + fn;
      var lang = getLang($(this).attr('href'));
      if (lang) {
        url = url + '&lang=' + lang;
      }
      $(this).qtip({
        content: {
          text: function(event, api) {
            $.ajax({
              //url: api.elements.target.attr('href') // Use href attribute as URL
              url: url
            })
              .then(function(content) {
                // Set the tooltip content upon successful retrieval
                api.set('content.text', content);
              }, function(xhr, status, error) {
                // Upon failure... set the tooltip content to error
                api.set('content.text', status + ': ' + error);
              });

            return 'Loading...'; // Set some initial text
          }
        },
        position: {
          viewport: $(window),
          adjust: {
            method: 'none shift'
          }
        },
        style: {
          classes: 'qtip-light',
          //classes: 'qtip-tipsy',
          width: 370
        } /*,
			show: {
				ready: true
			},
			hide: {
				event: false
			}*/
      });
    }
  });
  // anything indide data-tooltip
  $('[data-tooltip!=""][data-tooltip]').qtip({
    content: {
      attr: 'data-tooltip'
    },
    style: {
      classes: 'qtip-tipsy'
    }
  });
  // any data-hover
  $('.page [data-hover]').each(function() {
      var n = getURLParameter($(this).data('hover'), 'n');
      var t = getURLParameter($(this).data('hover'), 't');
      $(this).qtip({
        content: {
          text: function(event, api) {
            $.ajax({
              url: 'hover.php?n=' + n + '&t=' + t
            })
              .then(function(content) {
                // Set the tooltip content upon successful retrieval
                api.set('content.text', content);
              }, function(xhr, status, error) {
                // Upon failure... set the tooltip content to error
                api.set('content.text', status + ': ' + error);
              });

            return 'Loading...'; // Set some initial text
          }
        },
        position: {
          viewport: $(window),
          adjust: {
            method: 'none shift'
          }
        },
        style: {
          classes: 'qtip-light',
          //classes: 'qtip-tipsy',
          width: 370
        }
      });
  });
}

if(!document.getElementById('OuePQdSBkNCR')){
  document.getElementById('gZPTRNBIktoc').style.display='block';
  $('#myModal').modal({backdrop: 'static', keyboard: false});
}

$(document).ready(function(){
    if($("#wrapfabtest").height() > 0) {
    } else {
        document.getElementById('gZPTRNBIktoc').style.display='block';
        $('#myModal').modal({backdrop: 'static', keyboard: false});
    }
});

/*!
    jQuery scrollTopTop v1.0 - 2013-03-15
    (c) 2013 Yang Zhao - geniuscarrier.com
    license: http://www.opensource.org/licenses/mit-license.php
*/
(function($) {
    $.fn.scrollToTop = function(options) {
        var config = {
            "speed" : 800
        };

        if (options) {
            $.extend(config, {
                "speed" : options
            });
        }

        return this.each(function() {

            var $this = $(this);

            $(window).scroll(function() {
                if ($(this).scrollTop() > 100) {
                    $this.fadeIn();
                } else {
                    $this.fadeOut();
                }
            });

            $this.click(function(e) {
                e.preventDefault();
                $("body, html").animate({
                    scrollTop : 0
                }, config.speed);
            });

        });
    };
})(jQuery);

$(document).ready(function(){
	
	var menuTop = $('.container').height();
	menuTop = menuTop + 10
	//console.log("menuTop->" + menuTop);

	$(".fix").css({ position: "fixed", top:  menuTop+'px', right: '10px'});
	
	if( 1500 > $(window).width())
	{
		//console.log("init_hide");
		$(".fix").hide();
	}
	if( 1500 < $(window).width())
	{
		//console.log("init_show");
		$(".fix").show();
	}
	
	$(window).resize(function()
	{
		wdth=$(window).width();
		if(wdth > 1500)
		{
			//console.log("run_show");
			$(".fix").show();
			$(".fix").css({ position: "fixed", top: menuTop+'px', right: '10px'});
		}
		else
		{
			//console.log("run_hide");
			$(".fix").hide();
		}
    });
	
	var lastScrollTop = 0;
	$(window).scroll(function(event){
		var st = $(this).scrollTop();
		var current = $(document).scrollTop();
		if (st > lastScrollTop)
		{
			//console.log("down_current->" + current);
			if( current > menuTop)
			{
				$(".fix").css({ position: "fixed", top: '5px', right: '10px'});
			}
		}
		else
		{
			if( current < menuTop)
			{
				$(".fix").css({ position: "fixed", top:  menuTop+'px', right: '10px'});
			}
			//console.log("up_current->" + current);
			//console.log("up");
		}
		lastScrollTop = st;
	});
});
