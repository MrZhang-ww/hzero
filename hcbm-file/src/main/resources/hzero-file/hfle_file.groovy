package script.db

databaseChangeLog(logicalFilePath: 'script/db/hfle_file.groovy') {
    changeSet(author: "qingsheng.chen@hand-china.com", id: "2019-01-02-hfle_file") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hfle_file_s', startValue:"1")
        }
        createTable(tableName: "hfle_file", remarks: "") {
            column(name: "file_id", type: "bigint(20)", autoIncrement: true ,   remarks: "")  {constraints(primaryKey: true)} 
            column(name: "attachment_uuid", type: "varchar(" + 50 * weight + ")",   defaultValue:"0",   remarks: "附件集UUID")  {constraints(nullable:"false")}  
            column(name: "directory", type: "varchar(" + 60 * weight + ")",  remarks: "上传目录")   
            column(name: "file_url", type: "varchar(" + 480 * weight + ")",  remarks: "文件地址")  {constraints(nullable:"false")}  
            column(name: "file_type", type: "varchar(" + 120 * weight + ")",  remarks: "文件类型")   
            column(name: "file_name", type: "varchar(" + 240 * weight + ")",  remarks: "文件名称")  {constraints(nullable:"false")}  
            column(name: "file_size", type: "bigint(20)",  remarks: "文件大小")   
            column(name: "bucket_name", type: "varchar(" + 60 * weight + ")",  remarks: "文件目录")  {constraints(nullable:"false")}  
            column(name: "file_key", type: "varchar(" + 240 * weight + ")",  remarks: "对象KEY")  {constraints(nullable:"false")}  
            column(name: "md5", type: "varchar(" + 60 * weight + ")",  remarks: "文件MD5")   
            column(name: "tenant_id", type: "bigint(20)",   defaultValue:"0",   remarks: "租户ID")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }
   createIndex(tableName: "hfle_file", indexName: "hfle_file_n1") {
            column(name: "file_url")
            column(name: "bucket_name")
            column(name: "tenant_id")
        }
   createIndex(tableName: "hfle_file", indexName: "hfle_file_n2") {
            column(name: "attachment_uuid")
            column(name: "tenant_id")
        }
   createIndex(tableName: "hfle_file", indexName: "hfle_file_n3") {
            column(name: "file_key")
        }

    }
}