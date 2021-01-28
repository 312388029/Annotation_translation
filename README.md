### Mapper4的使用

翻译自定义注解
```
    package com.cy.common.annotation.Translate
```

翻译Service
```
    package com.cy.service.impl.TranslateService
```

翻译自定义aop
```
    package com.cy.common.aop.TranslateAspect
```

继承`Mapper<V>`
```
    public interface SysUserMapper extends Mapper<SysUser> {
```

实体类注解扩展
```
    //主键注解，开启主键自动生成策略，数据库自增值会在新增之后放入该属性中
    @KeySql(useGeneratedKeys = true)
    
    //字段注解，使该属性并非数据库表的字段的映射
    @Transient  //javax.persistence包
    
    // 字段注解，新增时排除该字段
    // 使用PostgreSQL时，新增方法无论如何都对吧ID带上，这个注解可以帮我们新增的时候吧主键排除在外，这样就可以使用数据库的默认自增序列了
    @Column(insertable = false)
```

### PageHelper的使用

使用**PageHelper**的静态方法设置**起始页数**和**每页条数**  
设置的分页只会影响下一行的列表查询  
将列表数据放入**PageInfo**中  
```
    PageHelper.startPage(1, 10);
    List<SysUser> user = sysUserMapper.selectAll();
    PageInfo<SysUser> page = new PageInfo<>(user);
```

### PageInfo属性

**nextPage**：下一页  
**prePage**：上一页  
**pageNum**：当前页  
**pageSize**：每页条数  
**pages**：总页数  
**total**：总记录数  
**firstPage**：第一页  
**lastPage**：最后一页  
**endRow**：最后一条数据的索引  
**startRow**：第一条数据的索引  

### 拦截器

**InterecotorConfig**类配置拦截器 

**SysTokenInterceptor**类：系统后台Token拦截 

### Spring Data Redis相关配置 

**RedisTemplateConfiguration**类，配置事务、多个Template、多个Database数据源 

### Spring Data Redis事务支持 
由于Redis的事务是通道式的，相当于执行一个命令集，该命令集统一执行，同时生效或不生效（报错） 
```
    redisTemplate.setEnableTransactionSupport(true);
```
上述代码开启事务后导致一整个流程的操作都处在通道中，前面修改过数据，导致后面查找数据时出现报错  
所以本项目不采用上述方案来控制Redis的事务  

本项目通过一下方式实现事务  
```
    try {
        hashRedisTemplate.execute(new SessionCallback<List<Object>>() {
            @SuppressWarnings({ "unchecked", "rawtypes" })
            @Override
            public List<Object> execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                // 需要同时完成的数据操作
                return operations.exec();
            }
        });
    } catch (Exception e) {
            e.printStackTrace();
    }
```
**get**类型的操作可以不使用事务包裹  
**set、delete、expire**等对数据进行实际操作，并且存在两条及以上时，需要用到事务控制来保证操作的同步性
**execute**需要使用**try catch**包裹，保证方法内部的**finally**块正确执行关闭连接的的操作  

需要注意的是，**Redis数据操作尽可能放在代码的最后执行**，避免执行完Redis数据操作后仍有代码报错导致无法回滚数据  



