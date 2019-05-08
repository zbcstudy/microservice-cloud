package com.wondertek.springcloud.redisson.properties;

import com.wondertek.springcloud.redisson.enums.LockModel;
import com.wondertek.springcloud.redisson.enums.Model;
import org.redisson.config.SslProvider;
import org.redisson.config.TransportMode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.net.URI;

/**
 * Created by win on 2019/5/6.
 */
@ConfigurationProperties(prefix = "redisson")
public class RedissonProperties {

    private Model model = Model.SENTINEL;
    private String codec = "org.redisson.codec.JsonJacksonCodec";
    private Integer thread;
    private Integer nettyThread;
    private TransportMode transportMode = TransportMode.NIO;

    //公共参数
    private Integer idleConnectionTimeout = 10000;
    private Integer pingTimeout = 1000;
    private Integer connectTimeout = 10000;
    private Integer timeout = 3000;

    private Integer retryAttempts = 3;
    private Integer retryInterval = 1500;
    private String password;
    private Integer subscriptionsPerConnection = 5;
    private String clientName;
    private Boolean sslEnableEndpointIdentification = true;
    private SslProvider sslProvider = SslProvider.JDK;
    private URI sslTruststore;
    private String sslTruststorePassword;
    private URI sslKeyStore;
    private String sslKeystorePassword;
    private Integer pingConnectionInterval = 0;
    private Boolean keepAlive=false;
    private Boolean tcpNoDelay=false;
    private Boolean referenceEnabled = true;
    private Long lockWatchdogTimeout=30000L;
    private Boolean keepPubSubOrder=true;
    private Boolean decodeInExecutor=false;
    private Boolean useScriptCache=false;
    private Integer minCleanUpDelay=5;
    private Integer maxCleanUpDelay=1800;
    //锁的模式 如果不设置 单个key默认可重入锁 多个key默认联锁
    private LockModel lockModel;

    //等待加锁超时时间 -1一直等待
    private Long attemptTimeout= 10000L;

    //数据缓存时间 默认30分钟
    private Long dataValidTime=1000*60* 30L;
    //结束

    @NestedConfigurationProperty
    private SingleServerConfig singleServerConfig;
    @NestedConfigurationProperty
    private MultipleServerConfig multipleServerConfig;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getCodec() {
        return codec;
    }

    public void setCodec(String codec) {
        this.codec = codec;
    }

    public Integer getThread() {
        return thread;
    }

    public void setThread(Integer thread) {
        this.thread = thread;
    }

    public Integer getNettyThread() {
        return nettyThread;
    }

    public void setNettyThread(Integer nettyThread) {
        this.nettyThread = nettyThread;
    }

    public TransportMode getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(TransportMode transportMode) {
        this.transportMode = transportMode;
    }

    public Integer getIdleConnectionTimeout() {
        return idleConnectionTimeout;
    }

    public void setIdleConnectionTimeout(Integer idleConnectionTimeout) {
        this.idleConnectionTimeout = idleConnectionTimeout;
    }

    public Integer getPingTimeout() {
        return pingTimeout;
    }

    public void setPingTimeout(Integer pingTimeout) {
        this.pingTimeout = pingTimeout;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getRetryAttempts() {
        return retryAttempts;
    }

    public void setRetryAttempts(Integer retryAttempts) {
        this.retryAttempts = retryAttempts;
    }

    public Integer getRetryInterval() {
        return retryInterval;
    }

    public void setRetryInterval(Integer retryInterval) {
        this.retryInterval = retryInterval;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSubscriptionsPerConnection() {
        return subscriptionsPerConnection;
    }

    public void setSubscriptionsPerConnection(Integer subscriptionsPerConnection) {
        this.subscriptionsPerConnection = subscriptionsPerConnection;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Boolean getSslEnableEndpointIdentification() {
        return sslEnableEndpointIdentification;
    }

    public void setSslEnableEndpointIdentification(Boolean sslEnableEndpointIdentification) {
        this.sslEnableEndpointIdentification = sslEnableEndpointIdentification;
    }

    public SslProvider getSslProvider() {
        return sslProvider;
    }

    public void setSslProvider(SslProvider sslProvider) {
        this.sslProvider = sslProvider;
    }

    public URI getSslTruststore() {
        return sslTruststore;
    }

    public void setSslTruststore(URI sslTruststore) {
        this.sslTruststore = sslTruststore;
    }

    public String getSslTruststorePassword() {
        return sslTruststorePassword;
    }

    public void setSslTruststorePassword(String sslTruststorePassword) {
        this.sslTruststorePassword = sslTruststorePassword;
    }

    public URI getSslKeyStore() {
        return sslKeyStore;
    }

    public void setSslKeyStore(URI sslKeyStore) {
        this.sslKeyStore = sslKeyStore;
    }

    public String getSslKeystorePassword() {
        return sslKeystorePassword;
    }

    public void setSslKeystorePassword(String sslKeystorePassword) {
        this.sslKeystorePassword = sslKeystorePassword;
    }

    public Integer getPingConnectionInterval() {
        return pingConnectionInterval;
    }

    public void setPingConnectionInterval(Integer pingConnectionInterval) {
        this.pingConnectionInterval = pingConnectionInterval;
    }

    public Boolean getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(Boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public Boolean getTcpNoDelay() {
        return tcpNoDelay;
    }

    public void setTcpNoDelay(Boolean tcpNoDelay) {
        this.tcpNoDelay = tcpNoDelay;
    }

    public Boolean getReferenceEnabled() {
        return referenceEnabled;
    }

    public void setReferenceEnabled(Boolean referenceEnabled) {
        this.referenceEnabled = referenceEnabled;
    }

    public Long getLockWatchdogTimeout() {
        return lockWatchdogTimeout;
    }

    public void setLockWatchdogTimeout(Long lockWatchdogTimeout) {
        this.lockWatchdogTimeout = lockWatchdogTimeout;
    }

    public Boolean getKeepPubSubOrder() {
        return keepPubSubOrder;
    }

    public void setKeepPubSubOrder(Boolean keepPubSubOrder) {
        this.keepPubSubOrder = keepPubSubOrder;
    }

    public Boolean getDecodeInExecutor() {
        return decodeInExecutor;
    }

    public void setDecodeInExecutor(Boolean decodeInExecutor) {
        this.decodeInExecutor = decodeInExecutor;
    }

    public Boolean getUseScriptCache() {
        return useScriptCache;
    }

    public void setUseScriptCache(Boolean useScriptCache) {
        this.useScriptCache = useScriptCache;
    }

    public Integer getMinCleanUpDelay() {
        return minCleanUpDelay;
    }

    public void setMinCleanUpDelay(Integer minCleanUpDelay) {
        this.minCleanUpDelay = minCleanUpDelay;
    }

    public Integer getMaxCleanUpDelay() {
        return maxCleanUpDelay;
    }

    public void setMaxCleanUpDelay(Integer maxCleanUpDelay) {
        this.maxCleanUpDelay = maxCleanUpDelay;
    }

    public LockModel getLockModel() {
        return lockModel;
    }

    public void setLockModel(LockModel lockModel) {
        this.lockModel = lockModel;
    }

    public Long getAttemptTimeout() {
        return attemptTimeout;
    }

    public void setAttemptTimeout(Long attemptTimeout) {
        this.attemptTimeout = attemptTimeout;
    }

    public Long getDataValidTime() {
        return dataValidTime;
    }

    public void setDataValidTime(Long dataValidTime) {
        this.dataValidTime = dataValidTime;
    }

    public SingleServerConfig getSingleServerConfig() {
        return singleServerConfig;
    }

    public void setSingleServerConfig(SingleServerConfig singleServerConfig) {
        this.singleServerConfig = singleServerConfig;
    }

    public MultipleServerConfig getMultipleServerConfig() {
        return multipleServerConfig;
    }

    public void setMultipleServerConfig(MultipleServerConfig multipleServerConfig) {
        this.multipleServerConfig = multipleServerConfig;
    }
}
