package de.erdlet.blogging.blog.jpa;

import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.PersistenceUnitTransactionType;
import org.hibernate.jpa.HibernatePersistenceProvider;

import javax.sql.DataSource;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class CustomPersistenceUnitInfo implements PersistenceUnitInfo {

	private static final String JPA_VERSION = "3.0";
	private static final PersistenceUnitTransactionType transactionType = PersistenceUnitTransactionType.RESOURCE_LOCAL;

	private final String persistenceUnitName;
	private final Properties properties;
	private final List<String> managedClasses;

	public CustomPersistenceUnitInfo(
			final String persistenceUnitName,
			final Properties properties, final List<String> managedClasses) {
		this.persistenceUnitName = persistenceUnitName;
		this.properties = properties;
		this.managedClasses = managedClasses;
	}

	@Override
	public String getPersistenceUnitName() {
		return persistenceUnitName;
	}

	@Override
	public String getPersistenceProviderClassName() {
		return HibernatePersistenceProvider.class.getName();
	}

	@Override
	public PersistenceUnitTransactionType getTransactionType() {
		return transactionType;
	}

	@Override
	public DataSource getJtaDataSource() {
		return null;
	}

	@Override
	public DataSource getNonJtaDataSource() {
		return null;
	}

	@Override
	public List<String> getMappingFileNames() {
		return List.of();
	}

	@Override
	public List<URL> getJarFileUrls() {
		return List.of();
	}

	@Override
	public URL getPersistenceUnitRootUrl() {
		return null;
	}

	@Override
	public List<String> getManagedClassNames() {
		return List.copyOf(managedClasses);
	}

	@Override
	public boolean excludeUnlistedClasses() {
		return false;
	}

	@Override
	public SharedCacheMode getSharedCacheMode() {
		return SharedCacheMode.UNSPECIFIED;
	}

	@Override
	public ValidationMode getValidationMode() {
		return ValidationMode.AUTO;
	}

	public Properties getProperties() {
		return properties;
	}

	@Override
	public String getPersistenceXMLSchemaVersion() {
		return JPA_VERSION;
	}

	@Override
	public ClassLoader getClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}

	@Override
	public void addTransformer(ClassTransformer transformer) {

	}

	@Override
	public ClassLoader getNewTempClassLoader() {
		return null;
	}
}
