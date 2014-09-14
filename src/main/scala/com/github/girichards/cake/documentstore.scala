
// http://www.cakesolutions.net/teamblogs/2011/12/19/cake-pattern-in-depth
// https://github.com/levkhomich/akka-tracing/wiki/Setup
// https://github.com/levkhomich/akka-tracing

sealed class Document {

}

trait DocumentStoreComponent {

  def documentStore: DocumentStore

  trait DocumentStore {
    def find(id: String): Document
    def store(document: Document)
  }

}

trait DocumentStoreJDBCComponent extends DocumentStoreComponent {

  def documentStore = new DocumentStoreJDBC()
  
  class DocumentStoreJDBC extends DocumentStore {
    def find(id: String): Document = { new Document() }
    def store(document: Document) = {
    	println("JDBC")
    }
  }

}

trait DocumentStoreMongoDBComponent extends DocumentStoreComponent {
  
  def documentStore = new DocumentStoreMongoDB()
  
  class DocumentStoreMongoDB extends DocumentStore {
    def find(id: String): Document = { new Document() }
    def store(document: Document) = {
    	println("MongoDB")
    }
  }

}

trait DocumentStoreCassandraComponent extends DocumentStoreComponent {

  def documentStore = new DocumentStoreCassandra()
  
  class DocumentStoreCassandra extends DocumentStore {
    def find(id: String): Document = { new Document() }
    def store(document: Document) = {
    	println("Cassandra")
    }
  }

}

trait DocumentServiceComponent {

  def documentService: DocumentService

  trait DocumentService {
    def find(id: String): Document
    def store(document: Document)
  }
}

trait DefaultDocumentServiceComponent extends DocumentServiceComponent {
  this: DocumentStoreComponent =>

  def documentService = new DefaultDocumentService

  class DefaultDocumentService extends DocumentService {
    def find(id: String) = documentStore.find(id)
    def store(document: Document) = documentStore.store(document)
  }

}

object ImplementItWithJDBC extends App {
  val docService = new DefaultDocumentServiceComponent with DocumentStoreJDBCComponent
  docService.documentService.find("")
}

object ImplementItWithCassandra extends App {
  val docService = new DefaultDocumentServiceComponent with DocumentStoreCassandraComponent
  docService.documentService.find("")
}

