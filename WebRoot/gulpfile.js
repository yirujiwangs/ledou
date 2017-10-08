var del          = require('del'),
    gulp         = require('gulp'),
  	sass         = require('gulp-ruby-sass'),
  	concat       = require('gulp-concat'),           //合并文件
  	plumber      = require('gulp-plumber'),          //异常报错不中断任务
  	cleanCss     = require('gulp-clean-css'),        //压缩css
    uglify       = require('gulp-uglify'),           //混淆压缩js
    htmlReplace  = require('gulp-html-replace'),
    autoprefixer = require('gulp-autoprefixer'),     //添加css前缀
    rev          = require('gulp-rev'),              //文件名添加MD5后缀
    revCollector = require('gulp-rev-collector')    //路径替换

// 删除文件
gulp.task('del', function(){
    return del(['./dist','./rev1','./rev2']);
});

// 编译scss并输出至scss的css目录下
gulp.task('sass', function(){
	return sass('./web/scss/ledouyaControl.scss')
	    .on('error', sass.logError)
      	.pipe(plumber())
        .pipe(autoprefixer({
        	browsers: ['last 5 versions'],
        	remove: false,
    	}))
		.pipe(gulp.dest('./web/styles'));
});
// 更改文件名
gulp.task('rev:css',function() {                 
    gulp.src(['./dist/styles/ledouyaControl.css'])
        .pipe(plumber())                             
        .pipe(rev())                                     
        .pipe(gulp.dest('./dist/styles'))                        
        .pipe(rev.manifest())                            
        .pipe(gulp.dest('./rev1'));                      
});
gulp.task('rev:js',function() {
    gulp.src('./web/scripts/**/*.js')
        .pipe(plumber())                             
        .pipe(rev())                                     
        .pipe(gulp.dest('./dist/scripts'))                        
        .pipe(rev.manifest())                            
        .pipe(gulp.dest('./rev2'));  
});

gulp.task('revC:css', function() {
    gulp.src(['./rev1/*.json', './web/index.html'])              
        .pipe(revCollector())                                  
        .pipe(gulp.dest('./dist'));                             
});
gulp.task('revC:js', function() {
    gulp.src(['./rev2/*.json', './dist/index.html'])              
        .pipe(revCollector())                                  
        .pipe(gulp.dest('./dist'));
});

// gulp.task('concat:js', function() {                 
//     gulp.src(['./web/scripts/**/*.js'])
//         .pipe(plumber())
//         .pipe(concat('ledouyaControl.js'))
//         .pipe(uglify())
//         .pipe(gulp.dest('./web/scripts'));                     
// });

// 将静态文件移动至发布文件夹中
gulp.task('buildfiles', function(){
    // CSS文件
    gulp.src('web/styles/ledouyaControl.css')
        .pipe(plumber())
        .pipe(cleanCss())
        .pipe(gulp.dest('dist/styles'));
    // html文件
    gulp.src('web/views/**/*.html')
        .pipe(gulp.dest('dist/views'));
    // 图片文件
    gulp.src('web/images/*')
        .pipe(gulp.dest('dist/images'));
});

gulp.task('watch', function(){
    gulp.watch(['web/scss/*.scss'],['sass']);
    // gulp.watch(['web/scripts/**/*.js'],['concat:js']);
});

// 发布任务
gulp.task('build',function(){
});